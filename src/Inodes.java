public class Inodes {
	
	private File[] fn = new File[1];
	
	public Inodes (File f){
		fn[0] = f;
	}
	
	public File getInodeFile (){
		return fn[0];
	}
	
	public String getFN(){
		return fn[0].printFileName();
	}
	
	public String prntOL(){
		return fn[0].printFileDetails();
	}
	
	
	public void addFile(String fN, String eXt, String oL){
		fn[0].addFile(fN, eXt, oL);
	}

}
