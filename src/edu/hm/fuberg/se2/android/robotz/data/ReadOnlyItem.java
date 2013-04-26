/**
 * Munich University for Applied Science, 
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Interface ReadOnlyItem restricts the access to Item data for other
 * layers.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public interface ReadOnlyItem {

	/**
	 * Gets the X coordinate of the Item.
	 * @return the X coordinate of the Item.
	 */
	double getXCoord();

	/**
	 * Gets the Y coordinate of the Item.
	 * @return the Y coordinate of the Item.
	 */
	double getYCoord();

	/**
	 * Gets the size of the Item.
	 * @return The size of the Item.
	 */
	double getSize();

}
