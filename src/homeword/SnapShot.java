package homeword;

import java.util.LinkedHashMap;
import java.util.Map;

public class SnapShot {
	
	public String getSnapShot(String historyData, String id) {
		// 截取字符串到id规定位置
		int end = historyData.indexOf("\n\n", historyData.indexOf(id));
		String verifyStr = end == -1 ? historyData : historyData.substring(0, end);
		// 将截取后的字符串进行检验返回结果
		String verifyResult = verify(verifyStr.split("\n\n"));
		return verifyResult.equals("") ? getCurrentInfo() : verifyResult;
	}
	
	Map<String, Animal> animalMap = new LinkedHashMap<String, Animal>();
	
	private String verify(String[] verifyStrs) {
		try {
			for (int i = 0; i < verifyStrs.length; i++) {
				String[] info = verifyStrs[i].split("\n");
				for (int j = 2; j < info.length; j++) {
					Animal a = new Animal();
					a.setAnimalId(info[0]);
					String[] animalInfo = info[j].split(" ");
					a.setAnimalId(animalInfo[0]);
					int x = Integer.parseInt(animalInfo[1]);
					int y = Integer.parseInt(animalInfo[2]);
					if (animalInfo.length > 3) {
						if (animalMap.get(animalInfo[0]).getX() == x
								&& animalMap.get(animalInfo[0]).getY() == y) {
							a.setX(x + Integer.parseInt(animalInfo[3]));
							a.setY(y + Integer.parseInt(animalInfo[4]));
						} else {
							return "Conflict found at ".concat(info[0]);
						}
					} else {
						a.setX(Integer.parseInt(animalInfo[1]));
						a.setY(Integer.parseInt(animalInfo[2]));
					}
					animalMap.put(animalInfo[0], a);
				}
			}			
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Invalid format.";
		}
		return "";
	}
	
	private String getCurrentInfo() {
		StringBuilder s = new StringBuilder();
		for (String id : animalMap.keySet()) {
			s.append(animalMap.get(id).getAnimalId()).append(" ")
				.append(animalMap.get(id).getX()).append(" ")
				.append(animalMap.get(id).getY()).append("\n");			
		}
		return s.toString();
	}
}
