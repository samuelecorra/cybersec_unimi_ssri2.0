% Fabio Scotti fabio.scotti@unimi.it
% Course: Intelligent systems for industry, supply chain and environment
% A Matalb demo for 
% - loading the IRIS dataset
% - preparing the variables
% - basic classification


close all
clear
clc

%% save on your HD the dataset and cast data
url = 'https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data';
filename = 'iris.dat';
outfilename = websave(filename,url);


% scan the file to get the value
fid = fopen(outfilename, 'r');
S = textscan(fid,'%f%f%f%f%s','delimiter',',') ;
fclose(fid);
measurements = [S{1}, S{2}, S{3}, S{4}];
species = S{5};
% Equivalent to "load fisheriris"




%% creating the feature matrix Problem  (X in the course)
P = measurements;

% plot the feature matric
figure
plotmatrix(P)
title('scatter plot of the features')



%% Targets  (Y in the course)
T = zeros(size(species));
for i = 1 : size(T,1)
    switch species{i}
        case 'Iris-setosa'
            T(i) = 0;
        case 'Iris-versicolor'
            T(i) = 1;
        case 'Iris-virginica'
            T(i) = 2;
    end
end
    
% Plot the targets
figure
bar(T)
xlabel('Sample')
ylabel('Class')


%% some descriptive statistics
% Compute statistics to analyze the problem
statisticsP = zeros(4, size(P,2));
statisticNames{1} = 'Mean';
statisticNames{2} = 'Std ';
statisticNames{3} = 'Min ';
statisticNames{4} = 'Max ';
statisticsP(1,:) = mean(P);
statisticsP(2,:) = std(P);
statisticsP(3,:) = min(P);
statisticsP(4,:) = max(P);

% Print the statistical figures of merit
fprintf('\t\tFeat 1\tFeat 2\tFeat 3\tFeat 4\n');
for i = 1 : 4
    fprintf('%s\t', statisticNames{i});
    for j = 1 : size(P,2)
        fprintf('%03.03f\t', statisticsP(i,j));
    end
    fprintf('\n')
end

%%
%%%%%%%%%%%%%%%%%%%% PLOTTING THE DATA %%%%%%%%%%%%%%%
% Plot the dataset
variableNames{1} = 'Sepal length (cm)';
variableNames{2} = 'Sepal width (cm)';
variableNames{3} = 'Petal length (cm)';
variableNames{4} = 'Petal width (cm)';
iCount = 1;
figure
for i = 1 : 4
    for j = 1 : 4
        if (i~=j)
            subplot(4, 4, iCount)
            gscatter(P(:,i), P(:,j), species,'rgb','osd');
            xlabel(variableNames{i})
            ylabel(variableNames{j})
        end
        iCount = iCount + 1;
    end
end




%%
%%%%%%%%%%%%%%%%%%%% VERY EASY TO CREATE A CLASSIFIER NOW %%%%%%%%%%%%%%%
% Linear Discriminant Analysis
lda = fitcdiscr(P,T);
ldaClass = resubPredict(lda);


% Classification error
errorVector = ldaClass ~= T;

figure
bar(errorVector);
xlabel('Sample')
ylabel('Classification Error')



%% Figures of merit
totalError = sum(errorVector);
stdError = std(errorVector);
totalErrorPerc = mean(errorVector)*100;
stdErrorPerc = stdError * 100;


fprintf('Total classification error = %d \n', totalError);
fprintf('Std of the classification error = %03.03f \n', stdError);
fprintf('Total classification error = %03.03f %% \n', totalErrorPerc);
fprintf('Std of the classification error = %03.03f %% \n', stdErrorPerc);

%Confusion Matrix
confusionMatrix = confusionmat(ldaClass,T)
