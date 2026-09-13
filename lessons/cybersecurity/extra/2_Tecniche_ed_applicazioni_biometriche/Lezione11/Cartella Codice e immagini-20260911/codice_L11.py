# !pip install deepface
# !pip install opencv-python

import numpy as np
import cv2
import matplotlib.pyplot as plt

from deepface import DeepFace
from google.colab.patches import cv2_imshow


# Carica l'immagine da analizzare
img_path1 = "Cindy24.jpg"
# Esegui l'analisi e ottieni i risultati
result1 = DeepFace.analyze(img_path1, actions = ['age', 'gender'])
# Stampa i risultati
result1

# Carica l'immagine da analizzare
img_path2 = "Cindy57.jpg"
# Esegui l'analisi e ottieni i risultati
result2 = DeepFace.analyze(img_path2, actions = ['age', 'gender'])
# Stampa i risultati
result2


# Load the image using OpenCV
image = cv2.imread(img_path1)

# Extract face region coordinates from the analysis results
face_region = result1[0]['region']
x, y, w, h = face_region['x'], face_region['y'], face_region['w'], face_region['h']

# Draw a rectangle around the face
cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 4)  # Green rectangle with thickness of 2

# Display the image using cv2_imshow as a substitute for cv2.imshow in Colab
cv2_imshow(image)


img_path1 = "Cindy50.jpg"
img_path2 = "Cindy57.jpg"
embedding_objs1 = DeepFace.represent(img_path1)
embedding_objs2 = DeepFace.represent(img_path2)

embedding1 = embedding_objs1[0]["embedding"]
embedding2 = embedding_objs2[0]["embedding"]
# assert isinstance(embedding, list)
# assert model_name == "VGG-Face" and len(embedding) == 2622
print(len(embedding1))
print(embedding1)


# Proviamo altri estrattori
models = ['VGG-Face', 'Facenet', 'OpenFace', 'DeepFace', 'DeepID', 'Dlib', 'ArcFace']

# Dictionary to hold embeddings
embeddings = {}
for model_name in models:
    try:
        # Extract embeddings using DeepFace.represent function
        embedding = DeepFace.represent(img_path=img_path1, model_name=model_name, enforce_detection=False)
        # Store the embedding in the dictionary
        embeddings[model_name] = embedding
        vettore_estratto = embedding[0]['embedding']
        # Print the size of the embedding vector
        print(f"Size of the embedding vector for {model_name}: {len(vettore_estratto)}")
    except Exception as e:
        print(f"An error occurred with {model_name}: {e}")




print(f"Size of the embedding vector for {model_name}: {len(embedding)}")
print(embedding)
print(embedding[0]['embedding'])

vettore_estratto = embedding[0]['embedding']
print(len(vettore_estratto))


import matplotlib.pyplot as plt
import numpy as np

# Assuming 'embedding1' is a list of values for the first image as you showed.

# Example embedding (replace with your actual embedding)
embedding = embedding1

# Normalize the embedding to [0, 1] for the image plot
embedding_normalized = (embedding - np.min(embedding)) / (np.max(embedding) - np.min(embedding))

# Create an image of 100 pixels in height and width equal to the size of the vector for the image plot
height = 1
embedding_image = np.tile(embedding_normalized, (height, 1))

# Plot the actual embedding using the bar graph
plot_embedding(embedding, 'Actual Embedding Bar Graph')

from scipy.spatial import distance

img_path1 = "Cindy50.jpg"
img_path2 = "Cindy57.jpg"
embedding_objs1 = DeepFace.represent(img_path1, enforce_detection=False)
embedding_objs2 = DeepFace.represent(img_path2, enforce_detection=False)

embedding1 = embedding_objs1[0]["embedding"]
embedding2 = embedding_objs2[0]["embedding"]


# ---------------------------------------------------------
# Distance and similarity metrics between two embeddings
# ---------------------------------------------------------
# NOTE:
# - Euclidean distance measures absolute displacement in the feature space
# - Cosine distance measures angular dissimilarity (ignores vector norm)
# - Cosine similarity is derived from cosine distance
# ---------------------------------------------------------

def calculate_metrics(embedding1, embedding2):
    print(f"Confronto fra {img_path1} e {img_path2}")

    # -------------------------
    # Euclidean distance
    # -------------------------
    # Measures how much the embedding moves in the feature space
    euclidean_dist = distance.euclidean(embedding1, embedding2)
    print(f"Euclidean Distance: {euclidean_dist}")

    # -------------------------
    # Cosine distance
    # -------------------------
    # Scipy returns a DISTANCE, not a similarity:
    # 0   -> identical direction
    # >0  -> increasing angular dissimilarity
    cosine_dist = distance.cosine(embedding1, embedding2)

    # Convert cosine distance to cosine similarity
    # Similarity ranges approximately in [-1, 1]
    # Higher values mean more similar embeddings
    cosine_sim = 1 - cosine_dist

    print(f"Cosine Distance: {cosine_dist}")
    print(f"Cosine Similarity: {cosine_sim}")



# Use the previously obtained embeddings
calculate_metrics(embedding1, embedding2)


import matplotlib.pyplot as plt
from PIL import Image
import numpy as np

def plot_images_embeddings_delta_and_distance(img_path1, embedding1, img_path2, embedding2, title1, title2):
    # Load the original images
    original_image1 = Image.open(img_path1)
    original_image2 = Image.open(img_path2)

    # Calculate the delta between the two embeddings
    delta_embedding = np.array(embedding1) - np.array(embedding2)

    # Calculate the Euclidean distance components between the two embeddings
    distance_vector = np.square(delta_embedding)
    total_distance = np.sqrt(distance_vector.sum())  # Calculate the total Euclidean distance

    # Create a 2D array to visualize the distance as heatmap
    distance_graph = np.tile(distance_vector, (100, 1))  # Adjust the '100' to change the height of the heatmap

    # Create a figure to hold the images, embeddings, delta, and distance plot
    fig, axs = plt.subplots(3, 2, figsize=(15, 10))  # 3 rows, 2 columns

    # Plot the first original image
    axs[0, 0].imshow(original_image1)
    axs[0, 0].axis('off')  # Hide the axis
    axs[0, 0].set_title(title1)

    # Plot the second original image
    axs[0, 1].imshow(original_image2)
    axs[0, 1].axis('off')  # Hide the axis
    axs[0, 1].set_title(title2)

    # Plot the first embedding
    axs[1, 0].bar(range(len(embedding1)), embedding1)
    axs[1, 0].set_title(title1 + ' Embedding')

    # Plot the second embedding
    axs[1, 1].bar(range(len(embedding2)), embedding2)
    axs[1, 1].set_title(title2 + ' Embedding')

    # Plot the delta of the embeddings
    axs[2, 0].bar(range(len(distance_vector)), distance_vector)
    axs[2, 0].set_title('distance_vector')

    # Plot the Euclidean distance heatmap
    ax6 = axs[2, 1]
    im = ax6.imshow(distance_graph, interpolation='nearest', cmap=plt.cm.ocean)
    ax6.set_title(f"Euclidean Distance = {total_distance:.2f}")  # Display the total distance in the title
    plt.colorbar(im, ax=ax6)  # Show color bar

    # Show the plot with tight layout
    plt.tight_layout()
    plt.show()

# Example usage:
plot_images_embeddings_delta_and_distance(
    img_path1=img_path1,
    embedding1=embedding1,  # Your embedding list/array for Cindy24.jpg
    img_path2=img_path2,
    embedding2=embedding2,  # Your embedding list/array for Cindy57.jpg
    title1=img_path1,
    title2=img_path2
)


################# confronto

import matplotlib.pyplot as plt
from PIL import Image
import numpy as np

import matplotlib.pyplot as plt
from PIL import Image
import numpy as np

img_path1 = "Cindy24.jpg"
# img_path1 = "Rande.jpg"
# img_path1 = "Finiteleferie.jpg"

img_path2 = "Cindy57.jpg"

embedding_objs1 = DeepFace.represent(img_path1, enforce_detection=False)
embedding_objs2 = DeepFace.represent(img_path2, enforce_detection=False)

embedding1 = embedding_objs1[0]["embedding"]
embedding2 = embedding_objs2[0]["embedding"]


def plot_images_embeddings_delta_and_distance(img_path1, embedding1, img_path2, embedding2, title1, title2):
    # Load the original images
    original_image1 = Image.open(img_path1)
    original_image2 = Image.open(img_path2)

    # Calculate the delta between the two embeddings
    delta_embedding = np.array(embedding1) - np.array(embedding2)

    # Calculate the Euclidean distance components between the two embeddings
    distance_vector = np.square(delta_embedding)
    total_distance = np.sqrt(distance_vector.sum())  # Calculate the total Euclidean distance

    # Create a 2D array to visualize the distance as heatmap
    distance_graph = np.tile(distance_vector, (100, 1))  # Adjust the '100' to change the height of the heatmap

    # Create a figure to hold the images, embeddings, delta, and distance plot
    fig, axs = plt.subplots(3, 2, figsize=(15, 10))  # 3 rows, 2 columns

    # Plot the first original image
    axs[0, 0].imshow(original_image1)
    axs[0, 0].axis('off')  # Hide the axis
    axs[0, 0].set_title(title1)

    # Plot the second original image
    axs[0, 1].imshow(original_image2)
    axs[0, 1].axis('off')  # Hide the axis
    axs[0, 1].set_title(title2)

    # Plot the first embedding
    axs[1, 0].bar(range(len(embedding1)), embedding1)
    axs[1, 0].set_title(title1 + ' Embedding')

    # Plot the second embedding
    axs[1, 1].bar(range(len(embedding2)), embedding2)
    axs[1, 1].set_title(title2 + ' Embedding')

    # Plot the delta of the embeddings
    axs[2, 0].bar(range(len(distance_vector)), distance_vector)
    axs[2, 0].set_title('distance_vector')

    # Plot the Euclidean distance heatmap
    ax6 = axs[2, 1]
    im = ax6.imshow(distance_graph, interpolation='nearest', cmap=plt.cm.ocean)
    ax6.set_title(f"Euclidean Distance = {total_distance:.2f}")  # Display the total distance in the title
    plt.colorbar(im, ax=ax6)  # Show color bar

    # Show the plot with tight layout
    plt.tight_layout()
    plt.show()

# Example usage:
plot_images_embeddings_delta_and_distance(
    img_path1=img_path1,
    embedding1=embedding1,  # Your embedding list/array for Cindy24.jpg
    img_path2=img_path2,
    embedding2=embedding2,  # Your embedding list/array for Cindy57.jpg
    title1=img_path1,
    title2=img_path2
)