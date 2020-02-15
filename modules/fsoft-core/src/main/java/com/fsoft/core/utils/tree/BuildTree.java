package com.fsoft.core.utils.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fsoft.core.common.base.BaseTreeVo;
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
	 * F-Soft 返回树形
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param treeList
	 * @return
	 */
	public static final List<Tree> buildTree(List<? extends BaseTreeVo> treeList) {
		if (OgnlUtils.isEmpty(treeList))
			return new ArrayList<Tree>();
		List<Tree> trees = new ArrayList<Tree>();
		for (BaseTreeVo vo : treeList) {
			Tree nd = new Tree();
			nd.setId(vo.getId());
			nd.setCode(vo.getCode());
			nd.setTitle(vo.getName());
			nd.setField(vo.getCode());
			nd.setParentId(vo.getParentId());
			trees.add(nd);
		}
		return buildJsonArray(trees);
	}

	/****
	 * F-Soft 把树形列表对象，封装为树形结构（递归维护children属性）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param treeList
	 * @return
	 */
	public static List<Tree> buildJsonArray(List<Tree> treeList) {
		if (treeList == null) {
			return null;
		}
		// 声明根节点
		List<Tree> topNodes = new ArrayList<Tree>();
		for (Tree currNode : treeList) {
			String pId = currNode.getParentId();
			// 根节点
			if (OgnlUtils.isEmpty(pId) || "0".equals(pId)) {
				currNode.setParentId("");
				topNodes.add(currNode);
				continue;
			}
			// 针对找不到父节点的记录，设置为根节点
			boolean isTop = existTreeNode(treeList, pId);
			if (!isTop) {
				currNode.setParentId("");
				topNodes.add(currNode);
			}
		}
		// 循环构建树形
		for (Tree topNode : topNodes) {
			List<Tree> childrens = buildJsonChildrens(treeList, topNode);
			topNode.setChildren(childrens);
		}
		return topNodes;
	}

	/***
	 * F-Soft 递归构建当前根节点currNode及其所有下级节点的children属性
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param trees
	 * @param currNode
	 * @return
	 */
	private static List<Tree> buildJsonChildrens(List<Tree> trees, Tree currNode) {
		List<Tree> nodes = new ArrayList<Tree>();
		for (Tree node : trees) {
			if (OgnlUtils.isNotEmpty(node.getParentId()) && node.getParentId().equals(currNode.getId())) {
				List<Tree> chds = buildJsonChildrens(trees, node);
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
