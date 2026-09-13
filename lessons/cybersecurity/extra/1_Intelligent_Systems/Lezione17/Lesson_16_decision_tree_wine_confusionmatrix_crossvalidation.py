# Import required libraries
import pandas as pd
import numpy as np
from sklearn.datasets import load_wine
from sklearn.tree import DecisionTreeClassifier  #the real model
import matplotlib.pyplot as plt   
from sklearn import tree # the functions like tree.plot_tree()
from sklearn.model_selection import cross_val_score  # Add this import

# Load the Wine Dataset
wine = load_wine()
X, y = wine.data, wine.target

# 1. Convert to a DataFrame for easier manipulation and export
df = pd.DataFrame(data=X, columns=wine.feature_names)
df['target'] = y

# Define tree depth
tree_depth = 5

# 2. Train the Decision Tree Classifier
clf = DecisionTreeClassifier(max_depth=tree_depth)  # Limit depth for simplicity
clf.fit(X, y)

# Define the actual wine cultivar names
wine_cultivars = ['Barolo', 'Grignolino', 'Barbera']

# Visualize the tree
plt.figure(figsize=(20,10))
tree.plot_tree(clf, 
               feature_names=wine.feature_names, 
               class_names=wine_cultivars,  # Using actual wine names
               filled=True, 
               rounded=True, 
               fontsize=10)
plt.show()

# 3. Example: Classify an existing data point from the dataset
# Let's take the first sample (index 0) as an example
sample_existing = X[0].reshape(1, -1)  # Reshape for prediction
prediction_existing = clf.predict(sample_existing)
print("\nExample classification of an existing wine (first sample):")
print("Features:", dict(zip(wine.feature_names, X[0])))
print("Predicted class:", wine_cultivars[prediction_existing[0]])

# 4. Example: Classify a new, unseen wine sample
# Create a plausible new wine sample (values are reasonable based on dataset ranges)
new_wine = [[13.5, 2.0, 2.4, 20.0, 100.0, 2.8, 3.0, 0.3, 1.6, 5.0, 1.0, 3.0, 1000.0]]
prediction_new = clf.predict(new_wine)
print("\nClassification of a new, unseen wine:")
print("Features:", dict(zip(wine.feature_names, new_wine[0])))
print("Predicted class:", wine_cultivars[prediction_new[0]])

# 5. Create and visualize confusion matrix for training data
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay

# First analysis with shallow tree
tree_depth = 3
print(f"\n=== Analysis with Shallow Tree (depth={tree_depth}) ===")

# Train shallow tree
clf_shallow = DecisionTreeClassifier(max_depth=tree_depth)
clf_shallow.fit(X, y)

# Visualize shallow tree
plt.figure(figsize=(20,10))
tree.plot_tree(clf_shallow, 
               feature_names=wine.feature_names, 
               class_names=wine_cultivars,
               filled=True, 
               rounded=True, 
               fontsize=10)
plt.title(f"Decision Tree (depth={tree_depth})")
plt.show()

# Confusion matrix for shallow tree
y_pred_shallow = clf_shallow.predict(X)
cm_shallow = confusion_matrix(y, y_pred_shallow)
disp = ConfusionMatrixDisplay(confusion_matrix=cm_shallow, 
                             display_labels=wine_cultivars)
plt.figure(figsize=(10,10))
disp.plot(cmap='Blues', values_format='d')
plt.title(f'Confusion Matrix (Shallow Tree, depth={tree_depth})')
plt.show()

# Second analysis with deeper tree
tree_depth = 5
print(f"\n=== Analysis with Deeper Tree (depth={tree_depth}) ===")

# Train deeper tree
clf_deep = DecisionTreeClassifier(max_depth=tree_depth)
clf_deep.fit(X, y)

# Visualize deeper tree
plt.figure(figsize=(20,10))
tree.plot_tree(clf_deep, 
               feature_names=wine.feature_names, 
               class_names=wine_cultivars,
               filled=True, 
               rounded=True, 
               fontsize=10)
plt.title(f"Decision Tree (depth={tree_depth})")
plt.show()

# Confusion matrix for deeper tree
y_pred_deep = clf_deep.predict(X)
cm_deep = confusion_matrix(y, y_pred_deep)
disp = ConfusionMatrixDisplay(confusion_matrix=cm_deep, 
                             display_labels=wine_cultivars)
plt.figure(figsize=(10,10))
disp.plot(cmap='Blues', values_format='d')
plt.title(f'Confusion Matrix (Deep Tree, depth={tree_depth})')
plt.show()

# Final evaluation with cross-validation (using deeper tree)
n_samples = len(X)
train_accuracy = clf_deep.score(X, y)
cv_scores = cross_val_score(clf_deep, X, y, cv=10)
cv_mean = cv_scores.mean()

# Plot CV results
plt.figure()
plt.bar(range(1, 11), cv_scores)
plt.axhline(cv_mean, linestyle='--')
plt.xlabel("Fold")
plt.ylabel("Accuracy")
plt.title(f"10-fold CV Accuracy (n={n_samples} samples, depth={tree_depth})")
plt.tight_layout()
plt.show()

# Print final metrics
print("\n=== Final Metrics (Deep Tree) ===")
print("Training accuracy:", round(train_accuracy, 3))
print("Cross-validation accuracies:", np.round(cv_scores, 3))
print("Mean CV accuracy:", round(cv_mean, 3))
print("Variance of CV accuracies:", round(np.var(cv_scores), 3))