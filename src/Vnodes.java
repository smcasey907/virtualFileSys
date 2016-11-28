public class Vnodes {
	
	private Drives[] dv = new Drives[1];
	
	public Vnodes (Drives d){
		dv[0] = d;
	}
	
	public Drives getVnodeDrive (){
		return dv[0];
	}
	
	public char getDriveName(){
		return dv[0].getDriveName();
	}
	
	public int getInodeSize(){
		return dv[0].getInodeSize();
	}

	public String getFN(int loc){
		return dv[0].getFN(loc);
	}
	public String prntOL(int loc){
		return dv[0].prntOL(loc);
	}
	
	public void removeAFile(String fn){
		dv[0].removeInode(fn);
	}
	
	public void addFile(String fN, String eXt, String oL){
		dv[0].addFile(fN, eXt, oL);
	}
	
}
