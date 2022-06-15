//package start_pjt;

import java.io.*;
import java.util.*;

public class Main {
    static int inf = Integer.MAX_VALUE;
    static int[][] p,d;
    static int[] a,s;
    static int T,n;
    public static void main (String args[]) throws Exception {
        //System.setIn(new FileInputStream("C:\\example\\ex_11066"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++) {
            //st 	= new StringTokenizer(br.readLine());
            n = Integer.parseInt(br.readLine());
            a = new int[n+1];
            s = new int[n+1];
            p = new int[n+2][n+2];
            d = new int[n+2][n+2];
            st 	= new StringTokenizer(br.readLine());
            for(int i = 1 ; i<=n ; i++) {
                int x = Integer.parseInt(st.nextToken());
                a[i] = x;
                s[i] = s[i-1] + a[i];
            }
            for(int i= 1 ; i<=n; i++) {
                p[i][i] = i;
            }

            for (int l = 1; l<=n ; l++) {
                for(int i = 1; i+l <= n ; i++) {
                    int j = i+l;
                    d[i][j] = inf;
                    for(int k = p[i][j-1] ; k <= p[i+1][j] ; k++) {
                        if(d[i][j] > d[i][k]  + d[k+1][j]) {
                            d[i][j] = d[i][k]  + d[k+1][j];
                            p[i][j] = k;
                        }
                    }
                    d[i][j] += s[j] - s[i-1];
                }
            }
            System.out.println(d[1][n]);

        }//T
    }//main
}//ex
