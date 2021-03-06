/*
 * Copyright 2019, Huahuidata, Inc.
 * DataSphere is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 */

package com.datasphere.government.datalineage.gsp.dataflow.model;

import gudusoft.gsqlparser.TSourceToken;
import gudusoft.gsqlparser.nodes.TParseTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datasphere.government.datalineage.gsp.util.Pair;

public class ResultSet
{

	public static Map<String, Integer> DISPLAY_ID = new HashMap<String, Integer>( );
	public static Map<Integer, String> DISPLAY_NAME = new HashMap<Integer, String>( );

	private int id;
	private Pair<Long, Long> startPosition;
	private Pair<Long, Long> endPosition;
	private List<ResultColumn> columns = new ArrayList<ResultColumn>( );

	private TParseTreeNode gspObject;
	private boolean isTarget;

	public ResultSet( TParseTreeNode gspObject, boolean isTarget )
	{
		if ( gspObject == null )
		{
			throw new IllegalArgumentException( "ResultSet arguments can't be null." );
		}

		id = ++Table.TABLE_ID;

		this.gspObject = gspObject;
		this.isTarget = isTarget;

		TSourceToken startToken = gspObject.getStartToken( );
		TSourceToken endToken = gspObject.getEndToken( );
		if ( startToken != null )
		{
			this.startPosition = new Pair<Long, Long>( startToken.lineNo,
					startToken.columnNo );
		}
		else
		{
			System.err.println( );
			System.err.println( "Can't get start token, the start token is null" );
		}
		if ( endToken != null )
		{
			this.endPosition = new Pair<Long, Long>( endToken.lineNo,
					endToken.columnNo + endToken.astext.length( ) );
		}
		else
		{
			System.err.println( );
			System.err.println( "Can't get end token, the end token is null" );
		}
	}

	public Pair<Long, Long> getStartPosition( )
	{
		return startPosition;
	}

	public Pair<Long, Long> getEndPosition( )
	{
		return endPosition;
	}

	public List<ResultColumn> getColumns( )
	{
		return columns;
	}

	public void addColumn( ResultColumn column )
	{
		if ( column != null && !columns.contains( column ) )
		{
			this.columns.add( column );
		}
	}

	public TParseTreeNode getGspObject( )
	{
		return gspObject;
	}

	public int getId( )
	{
		return id;
	}

	public boolean isTarget( )
	{
		return isTarget;
	}

}
