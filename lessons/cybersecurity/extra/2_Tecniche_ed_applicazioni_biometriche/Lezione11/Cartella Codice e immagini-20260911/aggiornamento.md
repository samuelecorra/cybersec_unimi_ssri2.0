Att. il giorno 09/02/2026 e' stata corretta la seguente parte dello script

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
