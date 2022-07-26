package problem_4195;

import java.io.*;
import java.util.*;

public class Problem_4195_UnionFind {
	private static class SetNode {
		private String parent;
		private int size;

		public SetNode(String parent) {
			this.parent = parent;
			this.size = 1;
		}
	}

	private static class DisjointSet {
		private HashMap<String, SetNode> trees = new HashMap<>(); // key : child, value : parent

		public String find(String node) {
			if (!trees.containsKey(node)) {
				trees.put(node, new SetNode(node));
				return node;
			}

			while (!trees.get(node).parent.equals(node)) {
				node = trees.get(node).parent;
			}

			return node;
		}

		public void union(String node1, String node2) {
			String root1 = find(node1);
			String root2 = find(node2);

			if (root1.equals(root2)) {
				return;
			}

			SetNode parent = trees.get(root1);
			SetNode child = trees.get(root2);

			if (parent.size < child.size) {
				SetNode tmp = parent;
				parent = child;
				child = tmp;
			}

			child.parent = parent.parent;
			parent.size += child.size;
		}
	}

	private static StringBuilder answer = new StringBuilder();
	private static DisjointSet network;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(input.readLine());

		while (T-- > 0) {
			network = new DisjointSet();
			int F = Integer.parseInt(input.readLine());

			while (F-- > 0) {
				String[] friends = input.readLine().split(" ");
				network.union(friends[0], friends[1]);				
				answer.append(network.trees.get(network.find(friends[0])).size).append('\n');
			}
		}

		System.out.println(answer);
	}
}
