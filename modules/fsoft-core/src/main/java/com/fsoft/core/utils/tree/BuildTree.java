package com.fsoft.core.utils.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fsoft.core.utils.OgnlUtils;

/**
 * @package com.fsoft.core.utils.tree
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-09
 * @copyright 2009-2019
 */
public class BuildTree {
	/***
	 * @user Fish
	 * @date 2019-05-09
	 * @param nodes
	 * @return
	 */
	public static List<Tree> buildJsonArray(List<Tree> nodes) {
		if (nodes == null) {
			return null;
		}
		List<Tree> topNodes = new ArrayList<Tree>();
		for (Tree children : nodes) {
			String pId = children.getParentId();
			// 根节点
			if (OgnlUtils.isEmpty(pId) || "0".equals(pId)) {
				topNodes.add(children);
				continue;
			}
			// 针对找不到父节点的记录，设置为根节点
			boolean isTop = existTreeNode(nodes, pId);
			if (!isTop) {
				children.setParentId("");
				topNodes.add(children);
			}
		}
		// 循环构建树形
		for (Tree topNode : topNodes) {
			List<Tree> childrens = buildJsonChildrens(nodes, topNode);
			topNode.setChildren(childrens);
		}
		return topNodes;
	}

	/***
	 * @user Fish
	 * @date 2019-06-21
	 * @param allNodes
	 * @param parentNode
	 * @return
	 */
	private static List<Tree> buildJsonChildrens(List<Tree> allNodes, Tree parentNode) {
		List<Tree> nodes = new ArrayList<Tree>();
		for (Tree node : allNodes) {
			if (OgnlUtils.isNotEmpty(node.getParentId()) && node.getParentId().equals(parentNode.getId())) {
				List<Tree> chds = buildJsonChildrens(allNodes, node);
				node.setChildren(chds);
				nodes.add(node);
			}
		}
		return nodes;
	}

	/***
	 * 判断ID标识，在nodes列表是否存在对应的实体
	 * @user Fish
	 * @date 2019-06-21
	 * @param nodes
	 * @param id
	 * @return
	 */
	private static boolean existTreeNode(List<Tree> nodes, String id) {
		for (Tree parent : nodes) {
			if (StringUtils.equals(parent.getId(), id))
				return true;
		}
		return false;
	}
}
