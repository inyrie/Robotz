/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.view;

import android.view.MotionEvent;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;

/**
 * Class is responsible for converting pixel to model coordinates and vice versa.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-17
 */
class Converter {

	// /////////////// OBJECT VARIABLES /////////////////

	/** Defines the factor for the width to calculate from model to pixel coordinates. */
	private final double modelToPixelFactorX;

	/** Defines the factor for the height to calculate from model to pixel coordinates. */
	private final double modelToPixelFactorY;

	/** Defines the factor for the width to calculate from pixel to model coordinates. */
	private final double pixelToModelFactorX;

	/** Defines the factor for the height to calculate from pixel to model coordinates. */
	private final double pixelToModelFactorY;

	// /////////////// CTOR /////////////////

	/**
	 * Ctor.
	 * @param data The arena data.
	 * @param surfaceSizePixel The amount of pixel of the surface.
	 */
	Converter(final ReadOnlyArena data, final double surfaceSizePixel) {

		// robotzData = data;

		modelToPixelFactorX = surfaceSizePixel / data.getWidth();
		modelToPixelFactorY = surfaceSizePixel / data.getHeight();

		pixelToModelFactorX = data.getWidth() / surfaceSizePixel;
		pixelToModelFactorY = data.getHeight() / surfaceSizePixel;
	}

	// /////////////// CALCULATING METHODS /////////////////

	/**
	 * Method calculates the pixel coordinates to logical coordinates.
	 * @param event The touch event.
	 * @param targetSize The size of the target.
	 * @return The logical coordinates as double[].
	 */
	double[] pixelToModelCoords(final MotionEvent event, final double targetSize) {

		final double halfSize =  targetSize / 2;

		final double[] modelCoords = new double[2];

		modelCoords[0] = event.getX() * pixelToModelFactorX - halfSize;
		modelCoords[1] = event.getY() * pixelToModelFactorY - halfSize;

		return modelCoords;
	}

	/**
	 * Method calculates the logical coordinates to pixel coordinates.
	 * @param shift The delta for which the coordinates are shifted.
	 * @param xCoordinate The x coordinate of an Item.
	 * @param yCoordinate The y coordinate of an Item.
	 * @return The pixel coordinates as array.
	 */
	double[] modelToPixelCoords(final double xCoordinate, final double yCoordinate, final double shift) {

		// Returning the computed pixel coordinates as double[] array.

		return new double[] {(xCoordinate + shift) * modelToPixelFactorX, (yCoordinate + shift) * modelToPixelFactorY};
	}

	/**
	 * Method for computing pixel values from model values (used for drawing).
	 * @param modelValue The model value to convert into pixel values.
	 * @return The pixel values.
	 */
	float modelToPixelValues(final double modelValue) {
		return (float) (modelValue * Math.min(modelToPixelFactorX, modelToPixelFactorY));
	}

	/**
	 * Method for computing pixel value from model value for the x coordinate.
	 * @param modelValue The model value to convert into pixel value.
	 * @return The pixel value.
	 */
	float modelToPixelValuesX(final double modelValue) {
		return (float) (modelValue * modelToPixelFactorX);
	}

	/**
	 * Method for computing pixel value from model value for the y coordiante.
	 * @param modelValue The model value to convert into pixel value.
	 * @return The pixel value.
	 */
	float modelToPixelValuesY(final double modelValue) {
		return (float) (modelValue * modelToPixelFactorY);
	}
}
