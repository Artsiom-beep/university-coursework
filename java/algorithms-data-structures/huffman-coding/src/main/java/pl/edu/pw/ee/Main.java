package pl.edu.pw.ee;

import pl.edu.pw.ee.byteHuffMan.SimpleHuffman;
import pl.edu.pw.ee.byteHuffMan.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java -jar app.jar <input-file>");
            return;
        }

        String inputFile = args[0];
        String compressedFile = inputFile + ".huf";
        String restoredFile = inputFile + ".restored";

        try {
            // === 1. Read input file ===
            byte[] inputBytes = Files.readAllBytes(Path.of(inputFile));
            System.out.println("Input bytes: " + inputBytes.length);

            // === 2. Prepare Huffman structures ===
            int N = 1;
            ArrayList<Pair> pairs = new ArrayList<>();
            ArrayList<String> codes = new ArrayList<>();

            // === 3. COMPRESS ===
            ArrayList<Byte> compressed = SimpleHuffman.compress(inputBytes, N, pairs, codes);

            // convert to byte[]
            byte[] compressedArr = new byte[compressed.size()];
            for (int i = 0; i < compressed.size(); i++) {
                compressedArr[i] = compressed.get(i);
            }

            Files.write(Path.of(compressedFile), compressedArr);

            System.out.println("Compressed saved: " + compressedFile);
            System.out.println("Compressed size: " + compressedArr.length);

            // === 4. DECOMPRESS ===
            byte[] restoredBytes = SimpleHuffman.decompress(compressed, pairs, codes);

            Files.write(Path.of(restoredFile), restoredBytes);

            System.out.println("Restored saved: " + restoredFile);
            System.out.println("Restored size: " + restoredBytes.length);

            // === 5. Check correctness ===
            if (inputBytes.length == restoredBytes.length) {
                boolean ok = true;
                for (int i = 0; i < inputBytes.length; i++) {
                    if (inputBytes[i] != restoredBytes[i]) {
                        ok = false;
                        break;
                    }
                }
                System.out.println("Restored correctly: " + ok);
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}