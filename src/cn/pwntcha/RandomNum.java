package cn.pwntcha;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNum {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("result.csv");
		FileOutputStream fs = new FileOutputStream(file);
		List<Integer> pailie = new ArrayList<Integer>();
		for (int i = 100001; i < 999999 + 1; i++) {
			pailie.add(i);
		}
		Random r = new Random();
		r.setSeed(System.currentTimeMillis());
		int datasize = pailie.size();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < datasize; i++) {
			int randint = r.nextInt(pailie.size());
			Integer data = pailie.get(randint);
			sb.append(data.toString() + "\n");
			pailie.remove(randint);
			if (i % 1000 == 0)
				System.out.println(i);
		}
		fs.write(sb.toString().getBytes());
		fs.close();
	}

}
