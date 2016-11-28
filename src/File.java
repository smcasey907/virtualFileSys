public class File {
	
	private String fileName;
	private String extension;
	private String oneLine;
	
	public File (String fn, String ext, String ol){
		fileName = fn;
		extension = ext;
		oneLine = ol;
	}
	
	public String printFileDetails (){
		return oneLine;
	}
	
	public String printFileName(){
		return fileName + "." + extension;
	}
	
	public void addFile(String fn, String ext, String ol){
		fileName = fn;
		extension = ext;
		oneLine = ol;
	}

}
