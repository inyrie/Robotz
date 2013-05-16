/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.view;

import android.view.MotionEvent;
import edu.hm.fuberg.se2.android.robotz.data.Item;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;
import edu.hm.fuberg.se2.android.robotz.data.Target;

/**
 * Class is responsible for converting pixel to model coordinates and vice versa.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-17
 */
class Converter {

	/** The data object representing the arena. */
	private final ReadOnlyArena robotzData;
	/** Defines the factor for the width to calculate from model to pixel coordinates. */
	private final double modelToPixelFactorX;

	/** Defines the factor for the height to calculate from model to pixel coordinates. */
	private final double modelToPixelFactorY;

	/** Defines the factor for the width to calculate from pixel to model coordinates. */
	private final double pixelToModelFactorX;

	/** Defines the factor for the height to calculate from pixel to model coordinates. */
	private final double pixelToModelFactorY;

	/**
	 * Ctor.
	 * @param robotzData The data Object.
	 * @param width The surface width.
	 */
	Converter(final ReadOnlyArena data, final double surfaceSizePixel) {

		robotzData = data;

		modelToPixelFactorX = surfaceSizePixel / data.getWidth();
		modelToPixelFactorY = surfaceSizePixel / data.getHeight();

		pixelToModelFactorX = data.getWidth() / surfaceSizePixel;
		pixelToModelFactorY = data.getHeight() / surfaceSizePixel;
	}

	/**
	 * Method calculates the pixel coordinates to logical coordinates.
	 * @param event The touch event.
	 * @return the logical coordinates.
	 */
	Target pixelToModelCoords(final MotionEvent event) {

		final double halfSize = Target.TARGET_SIZE / 2;
		final double modelX = event.getX() * pixelToModelFactorX - halfSize;
		final double modelY = event.getY() * pixelToModelFactorY - halfSize;
		final double modelSize = Math.min(robotzData.getHeight(), robotzData.getWidth());

		if (modelX < modelSize && modelY < modelSize) {

			return new Target(modelX, modelY);
		}

		else {

			if (robotzData.getPlayer().getDestination() == null) {

				return null;
			}

			else {

				return new Target(robotzData.getPlayer().getDestination().getXCoord(), robotzData.getPlayer()
						.getDestination().getYCoord());
			}
		}
	}

	/**
	 * Method calculates the logical coordinates to pixel coordinates.
	 * @param item The item with logical coordinates.
	 * @param shift The delta for which the coordinates are shifted.
	 * @return The pixel coordinates as array.
	 */
	double[] modelToPixelCoords(final Item item, final double shift) {

		// Returning the computed pixel coordinates as double[] array.

		return new double[] {(item.getXCoord() + shift) * modelToPixelFactorX,
				(item.getYCoord() + shift) * modelToPixelFactorY};
	}

	/**
	 * Method for computing pixel values from model values (used for drawing).
	 * @param modelValue The model value to convert into pixel values.
	 * @return The pixel values.
	 */
	float modelToPixelValues(final double modelValue) {
		return (float) (modelValue * Math.min(modelToPixelFactorX, modelToPixelFactorY));
	}
}
