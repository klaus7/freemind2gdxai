package com.tr3030;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Basic Converter of Freemind mm files to gdx-ai files.
 */
public class FreemindToGdxAi {

    int depth = 0;
    File outputFile;
    private FileWriter fileWriter;

    public boolean readFreemindFile(String xml, String outputFilePath) throws IOException {
        if (outputFilePath != null) {
            outputFile = new File(outputFilePath);
            outputFile.createNewFile();
            fileWriter = new FileWriter(outputFile, true);
        }
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();
            NodeList nl = doc.getChildNodes();

            Node rootNode = getRootNode(nl);
            if (rootNode == null) {
                System.err.println("Couldn't find root node!");
                return false;
            }
            getImports(rootNode.getChildNodes());

            processNodeList(nl);

            return true;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }

    private Node getRootNode(NodeList nl) {
        int length = nl.getLength();
        for (int i = 0; i < length; i++) {
			Node item = nl.item(i);
			String localName = item.getNodeName();
            if (localName != null && localName.equals("node")) {
				// get root node
				return item;
			}
		}
        return null;
    }

    private void out(String o) throws IOException {
        if (outputFile != null) {
            fileWriter.append(o);
        } else {
            System.out.println(o);
        }
    }

    private Node getImports(NodeList nl) throws IOException {
        int length = nl.getLength();
        for (int i = 0; i < length; i++) {
			Node item = nl.item(i);
			String localName = item.getNodeName();
            if (localName != null && localName.equals("richcontent")) {
                String textContent = item.getTextContent();

                // remove empty lines
                textContent = textContent.replaceAll("(?m)^[ \t]*\r?\n", "");

                // remove preceding whitespace
                textContent = textContent.replaceAll("(?m)^\\s+", "");

                out(textContent);
				return item;
			}
		}
        return null;
    }

    private void processNodeList(NodeList nl) throws IOException {
        int length = nl.getLength();
        for (int i = 0; i < length; i++) {
			Node item = nl.item(i);
			NamedNodeMap attributes = item.getAttributes();
			if (attributes != null) {
				Node textNode = attributes.getNamedItem("TEXT");
                if (textNode != null) {
                    String textContent = textNode.getTextContent();

                    out(StringUtils.repeat("  ", depth) + textContent);

                    NodeList childNodes = item.getChildNodes();

                    depth++;
                    processNodeList(childNodes);
                    depth--;
                }
            }
		}
    }

    public static void main( String[] args ) throws ParseException, IOException {
        Options options = new Options();

        options.addOption("i", true, "The input file. (required)");
        options.addOption("o", true, "The output file. If none is provided, the output is printed to standard out.");
        options.addOption("f", false, "Force overwrite file if output file exists already.");
        options.addOption("?", false, "Print help.");

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("?")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar asteroidfight-server.jar", options);
            System.exit(0);
        }

        String inputFile = cmd.getOptionValue("i");
        String outputFilePath = cmd.getOptionValue("o");
        boolean forceOverwrite = cmd.hasOption("f");

        if (!cmd.hasOption("i")) {
            System.err.println("No input file provided.");
            System.exit(1);
        }

        if (!(new File(inputFile).exists())) {
            System.err.println("Input file not found!");
            System.exit(1);
        }

        if (outputFilePath != null) {
            File outputFile = new File(outputFilePath);
            if (outputFile.exists()) {
                if (forceOverwrite) {
                    boolean deleteResult = outputFile.delete();
                    if (!deleteResult) {
                        System.err.println("Output file exists and couldn't be removed!");
                        System.exit(1);
                    }
                } else {
                    System.err.println("Output file exists!");
                    System.exit(1);
                }
            }
        }

        FreemindToGdxAi app = new FreemindToGdxAi();
        app.readFreemindFile(inputFile, outputFilePath);
    }

}
