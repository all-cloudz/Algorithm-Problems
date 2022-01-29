package problem_10816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;


public class Problem_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> hashMap = new HashMap<>(N, 1.0f);

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }

        br.readLine();

        StringBuilder stringBuilder = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            String findKey = st.nextToken();
            stringBuilder.append(hashMap.getOrDefault(findKey, 0)).append(' ');
        }
        bw.write(String.valueOf(stringBuilder));
        bw.flush();

        br.close();
        bw.close();
    }
}