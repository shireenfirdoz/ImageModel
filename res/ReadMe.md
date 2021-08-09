# Project 5: Image Processing Application GUI

<div style="text-align: justify">Image Processing application is developed as part of Project 5 for CS5010: Programming Design Paradigm for the Spring 2021 semester taught by Prof. Maria Jump. This application has been developed incrementally over previous Project 3 and Project 4, the addition is a graphical user interface and synchronous controller for the view. The application is an image processing application that can perform operations over a given image. 

This application has been developed using the MVC architecture with a model, two controllers for the console and the GUI, and a view i.e. a Graphical User Interface.

The operations supported are image filtering operations like - blue filter or sharpen filter; image transformation operations like - grey scale transform or sepia tone transform; color reduction with and without essence; dithering; image chunking operations like - mosaic and pixelation; and pattern generation for a cross-stitched pattern using color closeness matching with DMC floss.

This application supports both the console execution as well as the GUI execution. The user can either interact using the console by specifying commands onto the console or the user can perform operations interactively via the Graphical User Interface. </div>

* Nearest color calculation for finding the nearest DMC color has been done using the Euclidean distance for the mosaic image art and for pattern generation redmean has been used for calculating the nearest DMC floss color.
* Color Reduction has been implemented with and without essence as reduction and dither respectively.
* Dithering has been implemented following the Floyd-Steinberg Algorithm.

# How to run (GUI)
1. Head over to the @ProjectDirectory.
2. java -jar ImageModel.jar --interactive
4. Click on Home → Load Image (This will load the image and it will be shown in the panel).
5. Click on Home → Image Operations → [... Select an operation to perform] (This will perform that operation on the image and the updated image will be visible in the same panel).
6. Batch File Operation [Home → Batch File]. This loads up a text area to write commands to execute. Use the button on the left to create a batch file for execution.

## Sample Executions Steps
1. Blur/Sharpen/Grey Scale/Sepia Tone [These operations have similar steps]
    > Home → Load Image
    > Home → Image Operations → Blur/Sharpen/Grey Scale/Sepia Tone
2. Color Reduction
    > Home → Load Image
    > Home → Image Operations → Reduce
    > Enter reduction value → Click OK
3. Dither
    > Home → Load Image
    > Home → Image Operations → Dither
    > Enter dither parameter → CLick OK
4. Mosaic
    > Home → Load Image
    > Home → Image Operations → Mosaic
5. Pixelate
    > Home → Load Image
    > Home → Image Operations → Pixelate
    > Enter Pixelation Value → Click OK
6. Cross Stitch Pattern
    > Home → Load Image
    > Home → Image Operations → Pixelate
    > Enter Pixelation Value → Click OK
    > Home → Cross Stitch Pattern → Generate Cross Stitch Pattern
7. Swap DMC in Cross Stitch Pattern
    > Home → Load Image
    > Home → Image Operations → Pixelate
    > Enter Pixelation Value → Click OK
    > Home → Cross Stitch Pattern → Generate Cross Stitch Pattern
    > Home → Cross Stitch Pattern → Swap DMC Color
    > Click on SuperPixel to swap
    > Select new Color from DMC Palette
    > Click Swap Button
8. Remove DMC Color
    > Home → Load Image
    > Home → Image Operations → Pixelate
    > Enter Pixelation Value → Click OK
    > Home → Cross Stitch Pattern → Generate Cross Stitch Pattern
    > Home → Cross Stitch Pattern → Remove DMC Color
    > Click on SuperPixel to remove
    > Click Remove DMC Button
9. Custom Cross Stitch Pattern
    > Home → Load Image
    > Home → Image Operations → Pixelate
    > Enter Pixelation Value → Click OK
    > Home → Cross Stitch Pattern → Generate Cross Stitch Pattern
    > Home → Cross Stitch Pattern → Custom Cross Stitch Pattern
    > Select a subset of DMC Colors from the palette shown
    > Click Generate
10. Batch File Operation
    > Home → Bath File
	> Place input image in resources (@ProjectDirectory/res/input) directory
    > Click on Load → Enter File Name → Click OK
    > Select Operation to Perform [Operations will ask for input (as required)]
    > Execute batch file
	> Provided sample batch file.
	> Output images will be generated in (@ProjectDirectory/res/output) directory

# How to run (Console)
1. Head over to the @ProjectDirectory.
2. Place input image in resources (@ProjectDirectory/res/input) directory
3. java -jar ImageModel.jar input.txt
4. Create a batch file with the below format sample file is attached
	> load SAMPLE_IMAGE.png
	> blur
	> save SAMPLE_IMAGEOUTPUT.png
	> quit
5. The output image which will be blurred as per the sample instruction above will be saved in resources (@ProjectDirectory/res/output) directory with the name updated as SAMPLE_IMAGE_BLUR.png

## Sample Execution (Console)
> java -jar ImageModel.jar -script input.txt

load SALAD1.png : loads the image file.
mosaic 200 :  perform mosaic operation
blur : perform blur operation
sharpen : perform sharpen operation
sepia : perform sepia operation
grey : perform grey operation
dither 8 : perform dither operation
reduce 8 : perform reduce operation
pixelate 50  : perform pixelation operation
save SALAD1Pixelate.png : save image file
pattern : perform cross stiched pattern.
savePattern SALAD1Pattern.txt : save the cross stiched pattern file in text format.

## Description of Examples for Console
Sample Images have been placed in the @ProjectDirectory/res/input and @ProjectDirectory/res/output directories.	

1)  Perform mosaic operation.
load SALAD1.png y
mosaic 200
save SALAD1Mosaic.png y

2) Perform blur operation.
load SALAD1.png y
blur
save SALAD1Blur.png y

3)  Perform sharpen operation.
load SALAD1.png y
sharpen
save SALAD1Sharpen.png y

4)  Perform sepia operation.
load SALAD1.png y
sepia
save SALAD1Sepia.png y

5)  Perform grey scale operation.
load SALAD1.png y
grey
save SALAD1Grey.png y

6)  Perform dither operation.
load SALAD1.png y
dither 8
save SALAD1Dither.png y

7)   Perform reduce operation.
load SALAD1.png y
reduce 8
save SALAD1Reduce.png y

8)  Perform pixelate operation.
load SALAD1.png y
pixelate 50
save SALAD1Pixelate.png y

9)  Perform pattern generation.
pattern
savePattern SALAD1Pattern.txt y

10) exit
quit

<div style="text-align: justify">The above sample code can be used to execute the image processing application. Variations of images are placed in the input directory with varying image height and width, to test for different scenarios.</div>


# Features Implemented
* Blur Filter
* Sharpen Filter
* Grey Scale Transformation
* Sepia Tone Transformation
* Color reduction without essence
* Dithering
	* Floyd-Steinberg Algorithm
* Mosaic Art Creation
* Pixelation
	* Superpixel calculation logic
	* Pixelation works on all image dimensions
* Cross - Stitched Pattern Generation
	* Color Distance calculation - Euclidean Distance/Redmean Distance
	* Swap DMC color with the color chosen from dmc pallete
	* Remove DMC color to blank and . symbol
	* Custom cross stiched DMC pattern generation using selective DMC colors
* Controller (Console and GUI)
* Graphical User Interface for user Interaction

# Changelog

1) Made changes in the load command to load the file from console or gui jfilechooser. Hence Changes in the command.
2) Added method in model for swap dmc color  pattern generation, remove dmc color pattern generation.
3) Command design pattern changes for cross stitched pattern so that view controller can interact with model via comman design pattern.
4) Added getDmcColor pallete method to model.
5) Added image attribute in cross stiched pattern to show the dmc color image.
6) CrossStitchedPattern imageMap datatype changed to accomodate the overlay requirement.
7) My model contains the image, image pattern and pixelation now, earlier I was passing it to controller and getting it from controller, so storing does not need to get the 
these information from controller to pass to model. 
8) Refactor code for command design pattern and Image Operator to remove the abstract class behaviour having null.
9) Added View controller to handle the control of gui
10) Build GUI using Swing, having custom component for dmc color pallete and legend.


# Assumptions

1)	If invalid input or invalid command is encountered then display the message in GUI for batch file execution and further commands will not be executed. 
2)	Keep the file in the res/input while executing the batch file from command line.
3)	Output will be generated in res/output.
4)	DMC is assumed to be 489 colors as provided in the project description.
5)	Unicode characters will be used to represent the symbols for cross stitched pattern.
6)	Only known commands will be executed and invalid commands will be returned as invalid command.
7)	Height and width of the image should be more than 10 pixels across.
8)	 Pattern generation is not doing pixelation, so it is expected to pixelate and then call pattern from GUI and console.
9)	 Pixelation parameter cannot be less than height and width of the image.
10)	 Pixelation parameter cannot be negative.
11)	 Input seed for mosaic cannot be greater than the product of image height and width.
12)	 Mosaic parameter cannot be negative.
13)	If invalid input or invalid command is encounter then display the message in console and stops the execution.
14)	 Filename will be validated on execution and not in constructor of command pattern design.
15)	Common input parameter validation will be done in Model to support both batch script and gui.
16)	 Only .png or .jpg via save is supported.
17)	Only text file is supported for pattern generation.
18)	User can load and save from any location from GUI.
19)	User can execute batch script from GUI.
20)	It is expected to load a file and then perform the image operation or cross stitched pattern operation.
21)	 Two separate controller one for console or commanline execution and other to support GUI interaction. Code duplication is handled using AbstractImageController reusing the command design pattern to perform common operations of batch file and GUI.
22) overlayed the symbol on image using graphic 2D.



# Features pending for implementation
<div style="text-align: justify">All the required features have been implemented as asked in the Project requirement document. The required image operations have been coded and have been tested thoroughly to be running fine. The observed limitations and also limitations resultant of the assumptions have been documented below.</div>

# Limitations
NA

# Citations
<div style="text-align: justify">All the images that have been used in sample execution of the developed application are my own and do not infringe any other work or agreement. Sample images have been used from the CS5010: Spring 2021 course website on Canvas and the rights belong to respective owners. This image has been used to compare results for the other images to check the functioning of the different image operations.<br>
All the code implementation is done individually by me without any help, certain code snippets have been referred from online sources to understand the concepts, but the implementation in the code base is my own.
DMC floss color has been referred from http://my.crazyartzone.com/dmc.asp</div>
1) Image Panel taken from open mic professor's demo.
2) command pattern design and mvc referred from pdp module material.

