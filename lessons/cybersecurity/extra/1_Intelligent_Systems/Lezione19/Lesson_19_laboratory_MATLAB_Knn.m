% Fabio Scotti fabio.scotti@unimi.it
% Course: Intelligent systems for industry, supply chain and environment
% A Matalb demo for
% - knn

close all
clear
clc

% please note that we did a previous coding session about simple
% classifiers in coding part of the Lesson 14
% Linear Discriminant Analysis
% lda = fitcdiscr(P,T);
% ldaClass = resubPredict(lda);




load fisheriris
X = meas;
Y = species;
% X is a numeric matrix that contains four petal measurements for 150 irises.
% Y is a cell array of character vectors that contains the corresponding iris species.

% just a simple plot
plotmatrix(X)



% Train a 5-nearest neighbor classifier.
% Standardize the noncategorical predictor data --> see lesson about encoding the outputs
kNN_model = fitcknn(X,Y,'NumNeighbors',5,'Standardize',1)

% Let's try to input a single vector
x = X(1,:);
label = predict(kNN_model,x)

% Do a cross-validation test
% The function will create SUBCLASSIFIERS to do a correct CrossValidation
cvmdl_results = crossval(kNN_model, 'KFold',10)
kfoldLoss(cvmdl_results )

fprintf('KFOLD Validation with K = %d --> Error = %f \n', 10, kfoldLoss(cvmdl_results));


for K = [9:-1:3]
    cvmdl_results = crossval(kNN_model, 'KFold',K);
    fprintf('KFOLD Validation with K = %d --> Error = %f \n', K, kfoldLoss(cvmdl_results));
end


