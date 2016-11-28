public class Drives {

	private Inodes[] inode = new Inodes[10];
	private int nodeSize = 0;
	private char driveName;
	
	public Drives(char dn){
		driveName = dn;
	}
	
	public void addInode(Inodes id){
		try
		{
			inode[nodeSize] = id;
		} catch (ArrayIndexOutOfBoundsException e) {
	         System.out.println("Array is out of Bounds"+e);			
		}
		
		nodeSize++;
	}
	
	public char getDriveName(){
		return driveName;
	}
	
	public int getInodeSize(){
		return nodeSize;
	}
	
	public String getFN(int loc){
		return inode[loc].getFN();
	}
	
	public String prntOL(int loc){
		return inode[loc].prntOL();
	}
	
	public void removeInode(String fn){
		boolean found = false;
		for (int i = 0; i < nodeSize; i++){
			if (inode[i].getFN().equals(fn)){
				found = true;
				for (int j = i; j < nodeSize; j++){
					inode[j] = inode[j+1];
				}
			}
		}
		if (found)
			nodeSize--;
	}

	public void addFile(String fN, String eXt, String oL){
		inode[nodeSize].addFile(fN, eXt, oL);
		nodeSize++;
	}
	
}
