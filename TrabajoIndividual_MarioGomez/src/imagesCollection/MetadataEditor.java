package imagesCollection;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffOutputSet;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldTypeASCII;

public class MetadataEditor {
    public void editMetadata(String imagePath) throws IOException, ImageReadException, ImageWriteException {
        try {
            File imageFile = new File(imagePath);
            ImageMetadata metadata = Imaging.getMetadata(imageFile);
    
            TiffOutputSet outputSet = null;
            if (metadata instanceof TiffImageMetadata) {
                TiffImageMetadata tiffMetadata = (TiffImageMetadata) metadata;
                outputSet = tiffMetadata.getOutputSet();
            }
    
            if (outputSet == null) {
                outputSet = new TiffOutputSet();
            }
    
            // Modificar los metadatos según sea necesario
            Calendar calendar = Calendar.getInstance();
            calendar.set(2022, Calendar.JANUARY, 1); // Establecer la fecha de captura
            outputSet.setGPSInDegrees(40.7128, -74.0060); // Establecer la posición GPS (Nueva York)
    
            // Ejemplo de cómo agregar un campo personalizado al metadato
            TiffField customField = TiffField.create(
                    ExifTagConstants.EXIF_TAG_USER_COMMENT,
                    FieldTypeASCII.FIELD_TYPE_ASCII,
                    "Comentario personalizado");
            outputSet.addExifField(customField);
    
            // Guardar los metadatos modificados en el archivo de imagen
            File outputFile = new File("output.jpg");
            Imaging.writeImageMetadata(imageFile, outputFile, outputSet);
        } catch (IOException | ImageReadException | ImageWriteException e) {
            // Manejar las excepciones aquí o relanzarlas si es necesario
            throw e;
        }
    }
}
