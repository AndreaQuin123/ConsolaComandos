package consolacomandos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ConsolaComandos {

    private File directoryActual = new File(this.getClass().getSimpleName());
    private File directoryNuevo;
    public String Mkdir(String nombre) {
        StringBuilder mensaje = new StringBuilder();
        File newFolder = new File(directoryActual, nombre);

        if (!newFolder.exists()) {
            newFolder.mkdirs();
            mensaje.append("\nCarpeta agregada de manera exitosa!\n");
            return mensaje.toString();
        } else {
            mensaje.append("\nUna carpeta con el nombre de " + newFolder + " ya existe.\n");
            return mensaje.toString();
        }
    }

    public String getPath(){
        return directoryActual.getAbsolutePath();
    }
    
    public String Mfile(String nombre) throws IOException {
        StringBuilder mensaje = new StringBuilder();
        File newFile = new File(directoryActual, nombre);

        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
                mensaje.append("\nArchivo creado de manera exitosa!\n");
                return mensaje.toString();

            } catch (IOException e) {
                mensaje.append("\n !ERROR!: no se pudo crear\n");
                return mensaje.toString();

            }

        } else {
            mensaje.append("\nUn archivo con el nombre de ").append(newFile).append(" ya existe.\n");
            return mensaje.toString();
        }

    }

    public void borrar(File carpeta) {
        if (carpeta.isDirectory()) {
            for (File file : carpeta.listFiles()) {
                borrar(file);
            }
        }
        carpeta.delete();
    }

    public String Rm(String nombre) {
        StringBuilder mensaje = new StringBuilder();
        directoryActual = new File(nombre);

        if (directoryActual.exists()) {
            if (directoryActual.isDirectory()) {
                borrar(directoryActual);
                mensaje.append("\nSe borraron los contenidos de la carpeta.\n");
                
            } else if (directoryActual.isFile()) {
                directoryActual.delete();
                mensaje.append("\nSe eliminó el archivo.\n");
            }
            return mensaje.toString();
        }

        mensaje.append("\n !ERROR!: no se pudo eliminar.\n");
        return mensaje.toString();
    }

    public String Cd(String nombre) {
        StringBuilder mensaje = new StringBuilder();
        if (!nombre.equals("...")) {
            directoryNuevo = new File(directoryActual, nombre);

            if (directoryNuevo.exists() && directoryNuevo.isDirectory()) {
                directoryActual = directoryNuevo;
                mensaje.append("\nSe cambio de dirección.\n");
                return mensaje.toString();
            } else {
                mensaje.append("\n !ERROR!: no se encontro la nueva dirección.\n");
                return mensaje.toString();

            }
        } else {
            directoryActual = new File(directoryActual.getParent());
            return mensaje.toString();
        }
    }

    public String info(File file) {
        StringBuilder mensaje = new StringBuilder();

        if (file.exists()) {
            mensaje.append("\nNOMBRE: " + file.getName());
            mensaje.append("\nUBICACION: " + file.getPath());
            mensaje.append("\nDIRECCION ABSOLUTA: " + file.getAbsolutePath());
            mensaje.append("\nBYTES: " + file.length());
            mensaje.append("\nULTIMA MODIF: " + new Date(file.lastModified()));

            if (file.isFile()) {
                mensaje.append("\nES UN ARCHIVO.\n");
            } else if (file.isDirectory()) {
                mensaje.append("\nES UN FOLDER.\n");
            }
            return mensaje.toString();
        } else {
            mensaje.append("\nNO EXISTE EL OBJETO.\n");
            return mensaje.toString();
        }
    }

    public String Dir() {
        if (directoryActual.isDirectory()) {
            File[] files = directoryActual.listFiles();
            if (files != null) {
                for (File file : files) {
                    return "\n" + info(file);
                }
            }
        }
        
        return null;
    }

    public String Date() {
        StringBuilder mensaje = new StringBuilder();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        mensaje.append("\nFecha Actual: " + dateFormat.format(today.getTime())+"\n");
        
        return mensaje.toString();

    }

    public String Time() {
        StringBuilder mensaje = new StringBuilder();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        mensaje.append("\nFecha Actual: ").append(dateFormat.format(today.getTime())+"\n");

        return mensaje.toString();
    }

    public String wr(String nombre, String contenido) {
        StringBuilder mensaje = new StringBuilder();
        File fileToWrite = new File(directoryActual, nombre);

        try (FileWriter writer = new FileWriter(fileToWrite)) {
            writer.write(contenido);
            mensaje.append("\nContenido escrito en el archivo: ").append(fileToWrite.getAbsolutePath());
        } catch (IOException e) {
            mensaje.append("\nError al escribir en el archivo: ").append(e.getMessage());
        }

        return mensaje.toString();
    }

    public String rd(String nombre) {
        StringBuilder mensaje = new StringBuilder();
        File fileToRead = new File(directoryActual, nombre);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String line;
            mensaje.append("Contenido del archivo ").append(fileToRead.getName()).append(":\n");

            while ((line = reader.readLine()) != null) {
                mensaje.append(line).append("\n");
            }

            return mensaje.toString();
            
        } catch (IOException e) {
            
            return "Error al leer el archivo: " + e.getMessage();
        }
    }

}
