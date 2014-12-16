import java.util.ArrayList;

public class UniformQuantizer {
	public void compressed (int numOfLevel){
		
		ArrayList <Integer> avgLevel = new ArrayList<>() ;
		ArrayList <Rang_Level> ranges = new ArrayList<>(); 
		ImageRW imgRW = new ImageRW();
		int n = 0 , newStart = 0;
		int range = 256 / numOfLevel ;
		
		for (int i = 0 ; i < numOfLevel ; i++){
			if (i == 0){
				ranges.add(new Rang_Level(0, range - 1 ));
				newStart = range;
			}
				
			else 
				ranges.add(new Rang_Level(newStart+1 , newStart + range - 1 ));
			
			n += range;
			avgLevel.add(n);		
			newStart = ranges.get(i).end;
		}
		int image [] [] = imgRW.readImage("lena.jpg");
		int [][] newPixel = new int [image.length][image.length];
		for (int i = 0 ; i < image.length ; i++){
			for (int j = 0 ; j < image.length ; j++){
				int img = image [i][j];
				for (int k = 0 ; k < ranges.size() ; k++){
					if ((img >= ranges.get(k).start) && (img <= ranges.get(k).end)){
						newPixel [i][j] = avgLevel.get(k);
					}
						
				}
				
			}
		}
		imgRW.writeImage(newPixel, "copy.jpg");
	}
	public void decompressed (){
		
	}

}



class Rang_Level {
	public int start;
	public int end;
	public Rang_Level(int s, int e) {
		this.start  = s;
		this.end = e;
	}
	
}
