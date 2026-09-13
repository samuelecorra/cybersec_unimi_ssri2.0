# Corso di Complementi di biometria
# Fabio Scotti fabio.scotti@unimi.it 
# Università degli Studi di Milano
#
# Acquisizioni di immagini biometriche: ottiche e sensori


# carichiamo le varie librerie necessarie
!pip install deepface
!pip install opencv-python

import numpy as np
import cv2
import matplotlib.pyplot as plt

from deepface import DeepFace
from deepface.commons import functions
from google.colab.patches import cv2_imshow
from scipy.spatial import distance


# List of image file names
file_names = ["girl2_f24.jpg", "girl2_f50.jpg", "girl2_f85.jpg", "girl2_f200.jpg", "man_f16mm.jpg", "man_f200mm.jpg"]

# Initialize an empty list to store the embeddings
embeddings = []

# Load each image and extract embeddings using VGG-Face model
for file_name in file_names:
    img = cv2.imread(file_name)  # Make sure the images are in your current directory or provide the full path
    img_representation = DeepFace.represent(img, model_name='VGG-Face', enforce_detection=False)

    # Ensure that img_representation is a list or a numpy array
    if isinstance(img_representation, dict):
        # Extract the numerical vector.
        img_representation = img_representation.get('embedding', [])
    elif isinstance(img_representation, list) or isinstance(img_representation, np.ndarray):
        # If it's already a list or np.array, use it directly
        pass
    else:
        # If it's none of the above, log an error or raise an exception
        raise ValueError(f"Unexpected format for embeddings from image {file_name}")

    embeddings.append(img_representation)

# Convert to a numpy array for distance calculation
embeddings = np.array(embeddings)

print(embeddings)


from scipy.spatial import distance

# Assuming 'embeddings' is a list of vectors for which you want to compute the distances
# Initialize an empty matrix to store the distances
distance_matrix = [[0] * len(embeddings) for _ in range(len(embeddings))]

# Loop over each embedding
for i in range(len(embeddings)):
    for j in range(len(embeddings)):
        # Calculate Euclidean distance between embedding i and embedding j
        distance_matrix[i][j] = distance.euclidean(embeddings[i][0]["embedding"], embeddings[j][0]["embedding"])


# Plotting the confusion matrix with distances
fig, ax = plt.subplots()
cax = ax.matshow(distance_matrix, cmap='coolwarm')

# Add distance values to the confusion matrix
for (i, j), val in np.ndenumerate(distance_matrix):
    ax.text(j, i, f"{val:.2f}", ha='center', va='center')

# Add a color bar for reference
plt.colorbar(cax)

# Add titles and labels with the image names for clarity
plt.title('Matrix with Euclidean Distances')
plt.xlabel('Images')
plt.ylabel('Images')

# Set the tick positions and labels for both axes
tick_positions = range(len(file_names))
ax.set_xticks(tick_positions)  # Set the tick positions explicitly
ax.set_xticklabels(file_names, rotation=45, ha='left')  # Set the tick labels
ax.set_yticks(tick_positions)
ax.set_yticklabels(file_names)

# Show the plot
plt.show()
