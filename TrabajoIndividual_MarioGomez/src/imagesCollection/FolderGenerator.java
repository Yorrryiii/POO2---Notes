package imagesCollection;

import java.io.File;
import java.util.Random;

public class FolderGenerator {
    private int maxFoldersPerLevel;
    private int maxLevels;
    private String[] folderNames;

    public FolderGenerator(int maxFoldersPerLevel, int maxLevels, String[] folderNames) {
        this.maxFoldersPerLevel = maxFoldersPerLevel;
        this.maxLevels = maxLevels;
        this.folderNames = folderNames;
    }

    public void generateFolders(String basePath, int level) {
        if (level > maxLevels) {
            return;
        }

        Random random = new Random();
        int numFolders = random.nextInt(maxFoldersPerLevel) + 1;

        for (int i = 0; i < numFolders; i++) {
            String folderName = folderNames[random.nextInt(folderNames.length)];
            String folderPath = basePath + File.separator + folderName;
            File folder = new File(folderPath);
            folder.mkdirs();

            generateFolders(folderPath, level + 1);
        }
    }
}