# Project_IP
Project for my first coding course at my computer science bachelors. The course was called Introduction to Programming. 

The classes Color.java, ColoImage.java and ImageUtil.java were given by the professors. 

## ColorGraphics 
The main functions of this class are:
  * columns2D
  * columns2DBlurred
  * scatterPlot
  * rotation
 
The auxiliar functions of this class are:
  * valueMax - this function returns the max value of and int vector
  * newColor - this function allows the creation of a new color performing the validations needed for it to be a valid color
  

### Columns2D
This function allows the creation of a object of the instance ColorImage of a bar chart. It requires an object of the int vector type for the data that it will be handling and other object that will determine the visual aspect of the chart, such as the width of each bar, the space between bars and the color of the bar.

### Columns2DBlurred
This function will create a bar chart in each the bars color with blurred limits.

### ScatterPlot
This fucntion will create and object of the instance ColorImage of a scatter plot. It requires an object of the int vector type for the data that it will be handling and other object that will determine the visual aspect of the plot, such as the radius of the point, the space between points and the color of each point.

### Rotation
This function returns an object of the ColorImage type that is the 90 degree rotation clock wise of another ColorImage object.
