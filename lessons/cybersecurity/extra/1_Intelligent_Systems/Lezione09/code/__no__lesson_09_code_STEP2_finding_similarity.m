% Fabio Scotti fabio.scotti@unimi.it
% Course: Intelligent systems for industry, supply chain and environment
% A Matalb demo for 
% - image similarity 
% - convolution
% - automatic detection of image differences


close all
clear all  % do not use it in case of debug...

img_template = imread('find_difference_image_1.jpg');
img_subimage = imread('find_difference_image_2.jpg');
img_subimage2 = imread('find_difference_image_3.jpg');

whos



h1 = figure;
subplot(1,4,1)
imshow(img_template, [])
title('template')


subplot(1,4,2)
imshow(img_subimage, [])
title('subimage')

% color 2 gray conversion
img_template_gray = rgb2gray( img_template );
img_subimage_gray = rgb2gray( img_subimage );

%% inversion
img_template_gray = max(img_template_gray ) - img_template_gray  ;
img_subimage_gray = max(img_subimage_gray ) - img_subimage_gray  ;


h1 = figure;
subplot(1,4,1)
imshow(img_template, [])
title('template')


subplot(1,4,2)
imshow(img_subimage, [])
title('subimage')


%%


% crop a region 
% img_subimage_gray = img_subimage_gray (200:400, 200:400);

whos


 
img_template_gray_norm = img_template_gray - mean(mean(img_template_gray)) ;
img_subimage_gray_norm = img_subimage_gray - mean(mean(img_subimage_gray)) ;


% xcorr2(A,B) computes the crosscorrelation of matrices A and B.
crr = xcorr2(img_template_gray_norm , img_subimage_gray_norm);


%%
figure
subplot(2,2,3)
surf(crr);
shading interp;   % just to remove black line around the tiles
title('crr')

%%
% find the maximum
[ssr,snd] = max( crr(:) );                % operator (:) unfold the elements of the matrix in a 1D vector
[ij,ji] = ind2sub(size(crr),snd);

%%
hold on                                   % fix the plot to draw something later
plot3( ji, ij, ssr,'o')
xlabel('x');
ylabel('y');
zlabel('crr values');


%% 
% copy the original image  (**just for the EXTERNAL FRAME**)
img_diff = img_template_gray ;

% Place the smaller image inside the larger image. Rotate the smaller image 
% to comply with the convention that MATLAB® uses to display images. 
% the following line is just for debugging
% img_diff(ij:-1:ij-size(img_subimage_gray,1)+1,ji:-1:ji-size(img_subimage_gray,2)+1) = zeros(size( rot90(img_subimage_gray,2)));
% copy the SHIFTED SUBIMAGE (shift offset are processed with xcorr2()
% (this notation is not in the exam!!!)
img_diff(ij:-1:ij-size(img_subimage_gray,1)+1,ji:-1:ji-size(img_subimage_gray,2)+1) = ...
    rot90(img_subimage_gray,2) ;

figure(h1)
subplot(1,4,3)
imshow( img_diff )
title('just shift before substraction')



% img_diff2 = the template - the shifted subimage (with just the frame of template)
% we use double since we need negative values (not allowed in the uint8 format
img_delta = double(zeros(size(img_template_gray)));
img_delta = double(img_template_gray) - double(img_diff);


% figure(h1)
subplot(1,4,4)
imshow( img_delta, [] )
title('shift and substraction')

%%
figure
imshow( img_delta , [])
title('difference image')


%% improving the visualization
figure
subplot(1,2,1)
hist( img_delta(:), [2*255]);
xlabel('color bins')
ylabel('# of pixels')
title('Histogram of the delta image');

subplot(1,2,2)
[h_y, h_x]= hist( img_delta(:), [2*255]);
subplot(1,2,2)
semilogy(h_x, h_y);
xlabel('color bins')
ylabel('# of pixels')
title('Logaritmic histogram of the delta image');



%% finding the good thresholds 
max_value = max(img_delta(:));
min_value = min(img_delta(:));

additions = (img_delta > 0.5 * max_value ) ;
deletions = (img_delta < 0.5 * min_value );

figure
subplot(1,3,1)
imshow( img_delta , [])
title('difference image')

subplot(1,3,2)
imshow( additions , [])
title('additions')

subplot(1,3,3)
imshow( deletions , [])
title('deletions')


%% comparison
% the moon image is builtin in matlab
img_moon_gray = imread('moon.tif'); 
img_moon_gray_norm = img_moon_gray - mean(mean(img_moon_gray)) ;
img_template_gray_norm = img_template_gray - mean(mean(img_template_gray));

% xcorr2(A,B) computes the crosscorrelation of matrices A and B.
crr2 = xcorr2(double(img_template_gray_norm) , double(img_moon_gray_norm) );

h3 = figure;
subplot(2,3,1); 
imshow(img_template_gray_norm, []);
title('template norm.')

subplot(2,3,2); 
imshow(img_moon_gray_norm, []);
title('moon norm.')

subplot(2,3,3); 
surf(crr2);
shading interp;   % just to remove black line around the tiles
title('crr2')

% --------------- second row

% xcorr2(A,B) computes the crosscorrelation of matrices A and B.
img_crop = img_moon_gray_norm( [(1+50):(end-50)], [(1+50):(end-50)]) ;

crr3 = xcorr2( double( img_moon_gray_norm) , double(img_crop)  ) ;


subplot(2,3,4); 
imshow(img_moon_gray_norm, []);
title('moon norm.')

subplot(2,3,5); 
imshow(img_crop, []);
title('moon crop norm.')

subplot(2,3,6); 
surf(crr3);
shading interp;   % just to remove black line around the tiles
title('crr3')


max3 = max(max(crr3))
max2 = max(max(crr2))
max3/max2


%% same image 
h4 = figure
crr4 = xcorr2( double( img_template_gray_norm) , double(img_template_gray_norm)  ) ;
surf(crr4);
shading interp;   % just to remove black line around the tiles
title('crr4 (same image)')


close all

%% similarity 
close all
img_cream = double(rgb2gray( imread( 'ice_creams.jpg')));
img_cream_norm =  img_cream - mean(mean(img_cream));


h5 = figure;
crr5 = xcorr2( double( img_cream_norm) , double(img_cream_norm)  ) ;
surf(crr5);
shading interp;   % just to remove black line around the 
title('crr5 (same image)')


% similarity in CNN...
img_single = double(rgb2gray( imread( 'icecream2.jpg')));
img_single_norm =  img_single - mean(mean(img_single));


h6 = figure;
subplot(1,3,1); imshow( img_cream_norm, []); title('ice creams');
subplot(1,3,2); imshow( img_single_norm, []); title('similar single ice cream (kernel)');
crr6 = xcorr2( double( img_cream_norm) , double(img_single_norm)  ) ;
subplot(1,3,3); 
surf(crr6);
shading interp;   % just to remove black line around the 
title('Crr')

%
img_single = double(rgb2gray( imread( 'panda.jpg')));
img_single_norm =  img_single - mean(mean(img_single));

% img_single_norm = ones(size(img_single_norm));

h6 = figure;
subplot(1,3,1); imshow( img_cream_norm, []); title('ice creams');
subplot(1,3,2); imshow( img_single_norm, []); title('a panda (kernel)');
crr6 = xcorr2( double( img_cream_norm) , double(img_single_norm)  ) ;
subplot(1,3,3); 
surf(crr6);
shading interp;   % just to remove black line around the 
title('Crr')







