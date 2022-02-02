# Project_IP
Project for my first coding course at my computer science bachelors. The course was called Introduction to Programming. 

The classes Color.java, ColoImage.java and ImageUtil.java were given by the professors.  

This project was develop using a plugin called pandionj. More information about this plugin can be found in (https://andre-santos-pt.github.io/pandionj/installation.html)

## ColorGraphics 
The main functions of this class are:
  * columns2D
  * columns2DBlurred
  * scatterPlot
  * rotation
 
The auxiliar functions of this class are:
  * valueMax - this function returns the max value of and int vector
  * newColor - this function allows the creation of a new color performing the validations needed for it to be a valid color
  * tone - verifies the hue of a color
  * darker - returns which of the two colors is darker
 
There is also a function called testPart1 meant for the professor to test the project during the final presentation of the project.

### Columns2D
This function allows the creation of a object of the instance ColorImage of a bar chart. It requires an object of the int vector type for the data that it will be handling and other object that will determine the visual aspect of the chart, such as the width of each bar, the space between bars and the color of the bar.

### Columns2DBlurred
This function will create a bar chart in each the bars color with blurred limits.

### ScatterPlot
This fucntion will create and object of the instance ColorImage of a scatter plot. It requires an object of the int vector type for the data that it will be handling and other object that will determine the visual aspect of the plot, such as the radius of the point, the space between points and the color of each point.

### Rotation
This function returns an object of the ColorImage type that is the 90 degree rotation clock wise of another ColorImage object.

## Graphic
This class includes a construction of an object called graphic. This type of object will be composed of an object of the type ColorImage and 3 strings. 
This class will include methods to allow us the construction of a graphic based on just a ColorImage or based on a ColorImage and 3 strings. We will be able to change the strings associated to a graphic. We can make a graphic become transparent. We can return the ColorImage associated to a certain graphic. We can return the strings associated to a graphic.
This class included a function called testPart2 meant for the professor to test the project during the final presentation of the project.

## OverlapGraphics
This class includes a new type of object called OverlapGraphics which is a vector of objects of the type graphic. We can create a pill of graphics. Add a new graphic to the top of the graphic pile. Remove a a graphic from the top of the pile. Return the graphic that is on the top of the pile. Add a new graphic to a position of the pile which does not have to be the top. Switch the postion of two graphics that exist in the pile. Remove the title of a graphic. Organize the graphics in the pile in alphabetical order. Return a ColorImage of the overlap of all the ColorImages of the graphics in the pile. Return a ColorImage which is the 90 degree clockwise rotation of the overlap image of all graphics in the pile.
This class included a function called testPart3 meant for the professor to test the project during the final presentation of the project.

