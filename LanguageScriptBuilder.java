import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;


class LanguageScriptBuilder {
    public static void main(String[] args) {
	try {
	    BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

	    System.out.println("Choose Language:");
	    System.out.println("1. French");
	    System.out.println("2. Spanish\n");

	    String userInput = consoleInput.readLine();
	    int choice = Integer.parseInt(userInput);

	    BufferedReader bufferedFileReader = new BufferedReader(new FileReader("Labels.txt"));

	    String line = null;
	    StringBuilder languageStringBuilder = new StringBuilder();
	    while ((line = bufferedFileReader.readLine()) != null)  {
		String[] lineParsed = line.split(";");
		String englishLabelValue = lineParsed[0].trim();
		String languageLabelValue = lineParsed[1].trim();

		languageStringBuilder.append("EXEC dbo.addLanuageLabel 'English',  '" 
			+ englishLabelValue.replaceAll("'","''") + "', '" + ((choice == 1) ? "French" : "Spanish") 
			+ "', '" + languageLabelValue.replaceAll("'","''") + "';\n");
	    }

	    BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter("Labels.sql", false));
	    bufferedFileWriter.write(languageStringBuilder.toString());

	    consoleInput.close();
	    bufferedFileReader.close();
	    bufferedFileWriter.close();
	} catch(IOException ioe) {
	    System.out.println("IOException: " + ioe.getMessage());
	}
    }
}
