package svg;

import javax.swing.plaf.IconUIResource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ConventSvgtoAndroidXml {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        String in = "/home/dev/Dropbox/Projects/glyphicons_pro/glyphicons-pro/glyphicons-pro/glyphicons-basic-2-0/svg/individual-svg/";
        String out = "/home/dev/git/LibreraReader/app/src/main/res/drawable/";

        List<Integer> ids = Arrays.asList(
                4,5,17,21,28,37,38,49,55,66,67,72,73,77,82,86,87,92,94,
                101,104,105,106,108,114,115,137,145,151,157,158,159,
                160,166,172,173,174,175,176,177,178,185,186,193,
                211,212,217, 218, 221,222,223,224,231,232,292,298,299,
                305,309,310,371,372,399,
                415,417,451,476, 477, 498,
                522,544,545, 578,589,599,
                600,614,631,632,636,647,648,649,650,695,
                739,
                822,885);

        File[] files = new File(in).listFiles();
        for (File file : files) {

            String content  = Files.readString(Path.of(file.getPath()));

            String name = file.getName();
            File outFile = new File(out,name);
            boolean has = false;
            for(int id:ids){
                if(name.contains("_"+id+"_")){
                    has = true;
                    break;
                }
            }
            if(!has){
                continue;
            }

            System.out.println(file);
            System.out.println(content);
            String path = content.replace("<svg id=\"glyphicons-basic\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 32 32\">","");
            path = path.replace("</svg>","").trim();
            System.out.println("==");
            System.out.println(path);
            System.out.println("==");



            String androidXml = "<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                    "    android:viewportHeight=\"32\"\n" +
                    "    android:viewportWidth=\"32\"\n" +
                    "    android:width=\"32dp\"\n" +
                    "    android:height=\"32dp\"\n" +
                    "    >\n";

            path = path.replaceAll("id=\"(\\w+-*\\w*-*\\w*)\"","");
            path = path.replace(" d=","android:fillColor=\"@color/black\"\n android:pathData=");

            androidXml += path+"\n";
            androidXml += "</vector>";

            System.out.println(outFile);
            System.out.println(androidXml);
            System.out.println("==");


            //break;

            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile.getPath().replace(".svg",".xml")));
            writer.write(androidXml);
            writer.close();




        }


    }
}