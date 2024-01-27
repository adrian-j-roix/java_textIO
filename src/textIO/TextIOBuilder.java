/* Adrian J Roix
 * 
 * --TextIOBuilder--
 * An API for building text-based 
 * prompts, inputs, and assets 
 * for displaying input after 
 * it has been collected.
 */

package textIO;

import java.util.ArrayList;
import java.util.Scanner;

public class TextIOBuilder {
	// Private fields
	private ArrayList<String> _prompts;
	private ArrayList<String> _inputs;
	private ArrayList<String> _inputAssets;
//	private ArrayList<String[]> _inputAssetsSplit;
	private ArrayList<String> _outputs;
	private boolean _useAssets;
	private int _counter;
	private final String INPUT_ASSET_DELIMITER;
	
	// Public properties
	public ArrayList<String> getPrompts() {
		return this._prompts;
	}
	public void setPrompts(ArrayList<String> prompts) {
		this._prompts = prompts;
	}
	public ArrayList<String> getInputs() {
		return this._inputs;
	}
	public void saveInput(String input) {
		this._inputs.add(input);
	}
	public ArrayList<String> getInputAssets() {
		return this._inputAssets;
	}
	public void setInputAssets(ArrayList<String> inputAssets) {
		this._inputAssets = inputAssets;
	}
	public ArrayList<String> getOutputs() {
		return this._outputs;
	}
	public void createOutput() {
		if (this._outputs != null) {
			this._outputs.clear();
		}
		for (int i = 0; i < this._inputAssets.size(); i++) {
			String[] splitAsset = this._inputAssets.get(i).split(this.INPUT_ASSET_DELIMITER);
//			this._inputAssetsSplit.add(splitAsset);
			String output = splitAsset[0] + this._inputs.get(i) + splitAsset[1];
			this._outputs.add(output);
		}
	}
	public int getCounter() {
		return this._counter;
	}
	public void restartCounter() {
		this._counter = 0;
	}
	public void incrCounter() {
		this._counter++;
	}
	
	public boolean useInputAssetsCheck() {
		return this._useAssets;
	}
	
	// Constructors
	public TextIOBuilder() {
		this._prompts = new ArrayList<String>();
		this._inputs = new ArrayList<String>();
		this._inputAssets = new ArrayList<String>();
//		this._inputAssetsSplit = new ArrayList<String[]>();
		this._outputs = new ArrayList<String>();
		this._useAssets = true;
		this.INPUT_ASSET_DELIMITER = "\\[input-value\\]";
	}
//	public TextIOManager(ArrayList<String> prompts) {
//		this._prompts = prompts;
//		if (this._prompts.size() != 0) {
//			this._inputs = new ArrayList<String>();
//			this.restartCounter();
//		}
//	}
//	public TextIOManager(ArrayList<String> prompts, ArrayList<String> inputAssets) {
//		this._prompts = prompts;
//		this._inputAssets = inputAssets;
//		if (this._prompts.size() != 0) {
//			this._inputs = new ArrayList<String>();
//			
//			this.restartCounter();
//			if (this._inputAssets.size() != 0) {
//				this._useAssets = true;
//			}
//		}
//	}
	
	// Methods
	public void promptInput_CLI() {
		Scanner inputScanner = new Scanner(System.in);
		for (int i = this.getCounter(); i < this.getPrompts().size(); i++) {
			System.out.print((this.getPrompts()).get(i));
			String input = inputScanner.next();
			this.saveInput(input);
			this.incrCounter();
		}
		inputScanner.close();
		this.restartCounter();
	}
	public void printInput_CLI() {
		if (!(this.useInputAssetsCheck())) {
			for (int i = this.getCounter(); i < this.getInputs().size(); i++) {
				System.out.println((this.getInputs()).get(i));
				this.incrCounter();
			}
		} else {
			this.createOutput();
			for (int i = this.getCounter(); i < this.getOutputs().size(); i++) {
				String printStr = this.getOutputs().get(i);
				System.out.println(printStr);
				this.incrCounter();
			}
		}
		this.restartCounter();
	}
	
	// Tests
	public static void testRun_1() {
		ArrayList<String> testPrompts = new ArrayList<String>();
		testPrompts.add("What is your favorite color? ");
		testPrompts.add("What is your favorite cheese? ");
		ArrayList<String> testAssets = new ArrayList<String>();
		testAssets.add("Your said your favorite color is [input-value]. That's awesome!");
		testAssets.add("Your favorite cheese is [input-value]... stinky.");
		TextIOBuilder testObj = new TextIOBuilder();
		testObj.setPrompts(testPrompts);
		testObj.setInputAssets(testAssets);
		testObj.promptInput_CLI();
		System.out.println();
		testObj.printInput_CLI();
	}
	
	// Main
	public static void main(String[] args) {
		testRun_1();
	}
	
}
