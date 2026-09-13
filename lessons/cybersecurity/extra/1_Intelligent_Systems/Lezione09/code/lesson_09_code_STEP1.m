% Fabio Scotti fabio.scotti@unimi.it
% Course: Intelligent systems for industry, supply chain and environment
%
% ******** STEP 1 ************
%
% A Matalb demo for 
% - image similarity 
% - convolution
% - automatic detection of image differences


close all
clear all  % do not use it in case of debug...

img_template = imread('find_difference_image_1.jpg');
img_subimage = imread('find_difference_image_3.jpg');
whos

h1 = figure;
subplot(1,4,1)
imshow(img_template, [])
axis on
title('template')


subplot(1,4,2)
imshow(img_subimage, [])
axis on
title('subimage')

%% color 2 gray conversion
img_template_gray = rgb2gray( img_template );
img_subimage_gray = rgb2gray( img_subimage );

%% inversion
img_template_gray = max(img_template_gray ) - img_template_gray  ;
img_subimage_gray = max(img_subimage_gray ) - img_subimage_gray  ;


figure (h1);
subplot(1,4,3)
imshow(img_template_gray, [])
axis on
title('template')


subplot(1,4,4)
imshow(img_subimage_gray, [])
axis on
title('subimage')


%%


% crop a region 
% img_subimage_gray = img_subimage_gray (200:400, 200:400);

whos

 
img_template_gray_norm = img_template_gray - mean(mean(img_template_gray)) ;
img_subimage_gray_norm = img_subimage_gray - mean(mean(img_subimage_gray)) ;

%%

figure
imshow(img_template_gray_norm, [])
axis on
title('subimage after mean sub.')
mean_val = mean(img_template_gray_norm(:))
min_val = min(img_template_gray_norm(:))
max_val = max(img_template_gray_norm(:))



%%
% xcorr2(A,B) computes the crosscorrelation of matrices A and B.
crr = xcorr2(img_template_gray_norm , img_subimage_gray_norm);


%%  ---------------- CRR PLOT
% find the maximum
[ssr,snd] = max( crr(:) );                % operator (:) unfold the elements of the matrix in a 1D vector
[ij,ji] = ind2sub(size(crr),snd);

figure
subplot(2,2,1)
surf(crr);
shading interp;   % just to remove black line around the tiles
title('crr (z=values)')

hold on                                   % fix the plot to draw something later
plot3( ji, ij, ssr,'go', 'MarkerSize', 15, 'LineWidth',3)
xlabel('x');
ylabel('y');
zlabel('crr values');

subplot(2,2,2)
imshow(crr,[]);
axis on
colorbar
title('crr')
hold on                                   % fix the plot to draw something later
plot( ji, ij, 'go', 'MarkerSize', 15, 'LineWidth',3)
xlabel('x');
ylabel('y');

subplot(2,2,3)
imshow(img_template_gray_norm, []);
title('img_template', 'interp' , 'none')
axis on

% creating a canvas full of zeros same size of the input image
img_diff = zeros(size(img_template_gray_norm)) ;
% placing in the ij e and ji coordinates the temaplate image for comparison
img_diff(ij:-1:ij-size(img_subimage_gray,1)+1,ji:-1:ji-size(img_subimage_gray,2)+1) = ...
    rot90(img_subimage_gray,2) ;

subplot(2,2,4)
imshow(img_diff, []);
axis on
title('found!', 'interp' , 'none')
