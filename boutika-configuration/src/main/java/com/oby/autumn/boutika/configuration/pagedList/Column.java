package com.oby.autumn.boutika.configuration.pagedList;

/**
 * Representation of a Column in an exported list.
 */
public class Column {

	/**
	 * Alignment options for a column.
	 */
	public enum Align {LEFT, CENTER, RIGHT};

	private final String id;
	private final String label;
	private boolean displayed;
	private final Align align;
	private final int width;

	public Column(final String id, final String label, final boolean displayed, final Align align, final int width) {
		super();
		this.id = id;
		this.label = label;
		this.displayed = displayed;
		this.align = align;
		this.width = width;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public void hide() {
		this.displayed = false;
	}

	public Align getAlign() {
		return align;
	}

	public int getWidth() {
		return width;
	}
}
