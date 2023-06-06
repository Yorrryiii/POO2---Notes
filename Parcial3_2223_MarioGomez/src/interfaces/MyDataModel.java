package interfaces;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;

import huerto.Agua;
import huerto.Cultivo;
import huerto.HuertoUrbano;
import huerto.Parcela;

import java.util.ArrayList;
import java.util.List;

public class MyDataModel extends AbstractTableModel implements TreeModel {
    private HuertoUrbano huerto;
    private Parcela parcelaSeleccionada;
    private List<TableModelListener> tableListeners;
    private List<TreeModelListener> treeListeners;

    public MyDataModel(HuertoUrbano huerto) {
        this.huerto = huerto;
        this.parcelaSeleccionada = null;
        this.tableListeners = new ArrayList<>();
        this.treeListeners = new ArrayList<>();
    }

    public void setParcelaSeleccionada(Parcela parcela) {
        parcelaSeleccionada = parcela;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (parcelaSeleccionada != null) {
            return parcelaSeleccionada.getCultivos().size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (parcelaSeleccionada != null) {
            List<Cultivo> cultivos = parcelaSeleccionada.getCultivos();
            if (rowIndex < cultivos.size()) {
                Cultivo cultivo = cultivos.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return cultivo.getNombre();
                    case 1:
                        return cultivo.getAgua();
                    case 2:
                        return cultivo.getNumPlantas();
                    case 3:
                        return parcelaSeleccionada.getCliente().getID();
                    case 4:
                        return parcelaSeleccionada.getCliente().getNombre();
                    case 5:
                        return parcelaSeleccionada.getCliente().getApellido();
                    case 6:
                        return parcelaSeleccionada.getCliente().getTelefono();
                }
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cultivo";
            case 1:
                return "Agua";
            case 2:
                return "Plantas";
            case 3:
                return "ID del cliente";
            case 4:
                return "Nombre";
            case 5:
                return "Apellido";
            case 6:
                return "Telefono";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return false; // No editable para la columna "ID del cliente"
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (parcelaSeleccionada != null) {
            List<Cultivo> cultivos = parcelaSeleccionada.getCultivos();
            if (rowIndex < cultivos.size()) {
                Cultivo cultivo = cultivos.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        if (aValue instanceof String) {
                            cultivo.setNombre((String) aValue);
                        }
                        break;
                    case 1:
                        if (aValue instanceof String) {
                            String aguaValue = ((String) aValue).toLowerCase();
                            if (aguaValue.equals("alta") || aguaValue.equals("media") || aguaValue.equals("baja")) {
                                cultivo.setAgua(Agua.valueOf(aguaValue));
                            }
                        }
                        break;
                    case 2:
                        if (aValue instanceof Integer) {
                            int numPlantas = (int) aValue;
                            cultivo.setNumPlantas(numPlantas);
                        }
                        break;
                    case 4:
                        if (aValue instanceof String) {
                            String nombre = (String) aValue;
                            parcelaSeleccionada.getCliente().setNombre(nombre);
                            fireTableRowsUpdated(rowIndex, rowIndex);
                        }
                        break;
                    case 5:
                        if (aValue instanceof String) {
                            String apellido = (String) aValue;
                            parcelaSeleccionada.getCliente().setApellido(apellido);
                            fireTableRowsUpdated(rowIndex, rowIndex);
                        }
                        break;
                    case 6:
                        if (aValue instanceof Integer) {
                            int telefono = (int) aValue;
                            parcelaSeleccionada.getCliente().setTelefono(telefono);
                            fireTableRowsUpdated(rowIndex, rowIndex);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        tableListeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        tableListeners.remove(l);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        treeListeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        treeListeners.remove(l);
    }
    
    @Override
    public Object getRoot() {
        return "Huerto Urbano - " + huerto.getTam();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof String) {
            String parentNode = (String) parent;
            if (parentNode.startsWith("Huerto Urbano")) {
                List<Parcela> parcelas = huerto.getParcelas();
                if (index >= 0 && index < parcelas.size()) {
                    Parcela parcela = parcelas.get(index);
                    return "Parcela del cliente con id " + parcela.getCliente().getID();
                }
            }
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof String) {
            String parentNode = (String) parent;
            if (parentNode.startsWith("Huerto Urbano")) {
                return huerto.getParcelas().size();
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Parcela;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof String && child instanceof String) {
            String parentNode = (String) parent;
            String childNode = (String) child;
            if (parentNode.startsWith("Huerto Urbano") && childNode.startsWith("Parcela del cliente con id")) {
                int parentId = extractClienteId(parentNode);
                int childId = extractClienteId(childNode);
                if (parentId > 0 && childId > 0) {
                    return childId - 1;
                }
            }
        }
        return -1;
    }

    private int extractClienteId(String node) {
        String[] parts = node.split(" ");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    public HuertoUrbano getHuertoUrbano() {
        return null;
    }

    public void setHuertoSeleccionado(HuertoUrbano newHuerto) {
    }

    public HuertoUrbano getHuertoSeleccionado() {
        return null;
    }
}