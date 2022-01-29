package eng_game_objects.comparators;

import java.util.Comparator;

import eng_game_objects.IObject;

public class ObjectComparatorZ implements Comparator<IObject> {

	@Override
	public int compare(IObject o1, IObject o2) {
		return Float.compare(o1.getPosition().z, o2.getPosition().z);
	}
	
}
