import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * <h3>demo</h3>
 * <p>面试题</p>
 *
 * @author : TANGXIAO
 * @date : 2021-05-06 17:28
 **/
public class SocialNetwork {
    private HashMap<String, List<String>> map = new HashMap<>();
    private HashSet<String> set = new HashSet<>();

    public void load(String fileName) {
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempStrings;
            do {
                line = br.readLine();
                if (line.isEmpty()) {
                    break;
                }
                tempStrings = line.split(",");
                set.add(tempStrings[0]);
                set.add(tempStrings[1]);
                if (map.containsKey(tempStrings[0])) {
                    map.get(tempStrings[0]).add(tempStrings[1]);
                } else {
                    List<String> tempList = new ArrayList<>();
                    tempList.add(tempStrings[1]);
                    map.put(tempStrings[0], tempList);
                }
                if (map.containsKey(tempStrings[1])) {
                    map.get(tempStrings[1]).add(tempStrings[0]);
                } else {
                    List<String> tempList = new ArrayList<>();
                    tempList.add(tempStrings[0]);
                    map.put(tempStrings[1], tempList);
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int count() {
        return set.size();
    }

    public int distance(String from, String to) {
        if (!set.contains(from)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(from);
        int distance = 0;
        int cur = 1;
        int next = 0;
        Set<String> tempSet = new HashSet<>(set);
        tempSet.remove(from);
        while (!queue.isEmpty()) {
            String curStr = queue.poll();
            cur--;
            for (String s : map.get(curStr)) {
                if (s.equals(to)) {
                    return distance;
                }
                if (tempSet.contains(s)) {
                    queue.offer(s);
                    next++;
                    tempSet.remove(s);
                }
            }
            if (cur == 0) {
                distance++;
                cur = next;
                next = 0;
            }
        }
        return -1;
    }

    public List<String> getFriends(String name) {
        List<String> temp = map.get(name);
        temp.sort(String::compareTo);
        return temp;
    }
}
