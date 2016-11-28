public class Root {
	private Vnodes[] vnode = new Vnodes[10];
	private int nodeSize = 0;
	private int totalFileSize = 0;
	private String[] fileName = new String[30];
	private int aFiles = 0;
	private int bFiles = 0;
	private int cFiles = 0;
	private int noOfFiles = 0;
	private int noOfInodes = 0;
	
	File[] fn = new File[10];
	Inodes[] in = new Inodes[30];
	
	public Root(){	
		fn[noOfFiles] = new File("Nibbles", "basic", "Eat numbers in order");
		noOfFiles++;
		fn[noOfFiles] = new File("Old", "text", "Yes, I gerw up playing on QBASIC in DOS");
		noOfFiles++;
		fn[noOfFiles] = new File("random", "data", "ABCdefGHIjkl and stuff");
		noOfFiles++;
		fn[noOfFiles] = new File("Password", "text", "List of passwords cleverly hidden in a file");
		noOfFiles++;
	
	
		for (int i = 0; i < noOfFiles; i++){
			in[i] = new Inodes(fn[i]);
			noOfInodes++;
		}

		Drives a = new Drives('a');
		Drives b = new Drives('b');
		Drives c = new Drives('c');
	
		a.addInode(in[0]);
		aFiles++;
		a.addInode(in[1]);
		aFiles++;
		b.addInode(in[2]);
		bFiles++;
		c.addInode(in[3]);
		cFiles++;
	
		Vnodes vn0 = new Vnodes(a);
		Vnodes vn1 = new Vnodes(b);
		Vnodes vn2 = new Vnodes(c);
	
		addVnode(vn0);
		addVnode(vn1);
		addVnode(vn2);
	
		setFileNumber(noOfFiles);
		
		for (int i = 0; i < noOfFiles; i++)
			addAFile(i, fn[i].printFileName());
		}
	
		public void addVnode(Vnodes vd){
			try
			{
				vnode[nodeSize] = vd;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Array is out of Bounds"+e);			
			}
		
			nodeSize++;
		}
	
	public char getDriveName(int loc){
		return vnode[loc].getDriveName();
	}
	
	public int getInodeSize(int loc){
		return vnode[loc].getInodeSize();
	}
	
	public String getFN(int iLoc, int jLoc){
		return vnode[iLoc].getFN(jLoc);
	}
	
	public void setFileNumber(int fs){
		totalFileSize = fs;
	}
	
	public int getFileNumber(){
		return totalFileSize;
	}
	
	public void addAFile(int iLoc, String fn) {
		fileName[iLoc] = fn;
	}
	
	public String getAFileName(int iLoc){
		return fileName[iLoc];
	}
	
	public void addNewFile(String fN, String eXt, String oL){
		addAFile(noOfFiles, fN + "." + eXt);
		fn[noOfFiles] = new File(fN, eXt, oL);
		in[noOfInodes] = new Inodes(fn[noOfFiles]);
		//Add the file to the next drive
		if(cFiles < aFiles && cFiles < bFiles)
		{
			vnode[2].getVnodeDrive().addInode(in[noOfInodes]);
			cFiles++;
		}
		else if (bFiles < aFiles){
			vnode[1].getVnodeDrive().addInode(in[noOfInodes]);
			bFiles++;
		}
		else {
			vnode[0].getVnodeDrive().addInode(in[noOfInodes]);
			aFiles++;
		}
		
		noOfFiles++;
		noOfInodes++;
		totalFileSize++;
	}
	
	public void deleteAFile(String fN){
		int toDel = 0;
		for (int i = 0; i < noOfFiles; i++){
			if (fileName[i] == fN){
				toDel = i;
			}
		}
		for (int i = 0; i < noOfFiles; i++){
			if (i == toDel){
				for (int j = i; j < noOfFiles; j++){
					fileName[j] = fileName[j+1];
				}
			}
		}
		fileName[noOfFiles] = null;
		
		for (int i = 0; i < 3; i++)
			vnode[i].removeAFile(fN);
		
		noOfFiles--;
		totalFileSize--;
	}
	
	public String printAFile(String fn){
		String output = null;
		int iVal = 0;
		int jVal = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < getInodeSize(i); j++){
				if(getFN(i, j).equals(fn)){
					iVal = i;
					jVal = j;
				}
		}
		
		output = vnode[iVal].prntOL(jVal);
		return output;
	}
	
	
}
