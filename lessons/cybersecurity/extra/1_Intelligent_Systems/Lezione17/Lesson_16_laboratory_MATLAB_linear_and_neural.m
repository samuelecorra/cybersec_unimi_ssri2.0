% Fabio Scotti fabio.scotti@unimi.it
% Course: Intelligent systems for industry, supply chain and environment
% A Matalb demo for
% - training linar regressor
% - plotting and visulazation
% - training a feedworward network
% - overfitting 


close all
clear
clc
nnet.guis.closeAllViews()  % to close all NN plots

%% creating a data with known distribution
x = [0:0.2:10];
noise = randn(size(x));
t = 2 * x - 5 + noise;


h1 = figure ;
plot(x,t, 'o');
title('Linear model plus noise');
xlabel('x');
ylabel('t');


%% create the linear model and train it
if (1) % optimized solution based on the invertion of the matrix of data
    net1  = newlind(x,t);
else  % not optimized this is the general learning for non-linear networks
    % net1 = perceptron;
    net1 = linearlayer;
    net1 = configure(net1,[0],0);
   
    % A little help to tune the inizial points
    net1.IW{1,1} = 3.1;
    net1.b{1} = -3.1
    
    [net1 , tr] = train(net1,x,t);   % WARNING --> Train/Validation auto.created
    figure; plotperform(tr)  % need more tuning..... :-(
end

%% visualization of the trained linear model 
view(net1)

% how to access the weight of the model
W = net1.IW{1,1} ;
b= net1.b{1} ;
fprintf('--------------------------------\n');
fprintf('trained linear model W = %f \n' , W);
fprintf('trained linear model b = %f \n' , b);
fprintf('--------------------------------\n');



%% model accuracy
% how to check the performance
y1 = net1(x);  % simulate the inputs 
perf = perform(net1,y1,t) % Error on **trainig**
net1.performFcn  % is telling you the error metrics (MSE for Lin.)
mse_check = sum((y1 - t).^2 )/ size(t,2);
mean_t = mean(t);
R2 = 1 - ( sum((t - y1).^2 )/sum((t - mean_t).^2) );
fprintf('------trained linear model------\n');
fprintf('Perf.         = %f \n' , perf );
fprintf('Type of index = %s \n' , net1.performFcn );
fprintf('MSE           = %f \n' , mse_check );
fprintf('R2            = %f \n' , R2 );
fprintf('--------------------------------\n');

% plot the output of the model 
figure(h1)
hold on
plot(x, y1, 'xg-')
legend('Train data', 'Linear Model')






%% % Let's face the OCCAM's RAZOR --> use a large network
net2 = feedforwardnet(3);

net2.divideParam.trainRatio = 1.0;
net2.divideParam.testRatio  = 0.0;
net2.divideParam.valRatio   = 0;

[net2  , tr] = train(net2,x,t);   

h2 = figure;
plotperform(tr)

view(net2)
y2 = net2(x);
perf = perform(net2,y2,t) % Error on **trainig**


%%
h3 = figure
plot(x,t, 'o');
title('PROBLEM 01 liner data plus noise -- FFNN 3 neur.');
xlabel('x');
ylabel('t');

hold on
plot(x,y2, 'xr-');
xlabel('x');
ylabel('t');
legend('Train data', 'nn3')


%% Let's create a real overfitting
% Let's face the OCCAM's RAZOR --> use *very large* network
net3 = feedforwardnet(100);
net3.divideParam.trainRatio = 1.0;
net3.divideParam.testRatio  = 0.0;
net3.divideParam.valRatio   = 0;

[net3  , tr] = train(net3,x,t);   
figure(h2); plotperform(tr)
view(net3)
y3 = net3(x);

%%

h4 = figure 
subplot(1,2,1)
plot(x,t, 'o');
title('PROBLEM 01 liner data plus noise -- FFNN 100 neurons');
xlabel('x');
ylabel('t');
hold on
plot(x,y3, 'db');
legend('train data', 'nn100 on train data')


%% test point
x_bis = [0:0.0005:10];
y3_bis = net3(x_bis);

perf = perform(net3,y3,t) % Error on **trainig**

subplot(1,2,2)
plot(x,t, 'o');
title('PROBLEM 01 liner data plus noise -- FFNN 100 neurons');
xlabel('x');
ylabel('t');
hold on
plot(x_bis,y3_bis, '.k');
legend('train data', 'nn100 on validation data' )



