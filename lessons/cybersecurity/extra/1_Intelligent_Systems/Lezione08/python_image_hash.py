# MD5 and SHA1 hashes for image comparison
import hashlib

def compute_hashes(image_path):
    with open(image_path, 'rb') as f:
        data = f.read()
        md5_hash = hashlib.md5(data).hexdigest()
        sha1_hash = hashlib.sha1(data).hexdigest()
    return md5_hash, sha1_hash

# Example usage
img1_md5, img1_sha1 = compute_hashes("image1.jpg")
img2_md5, img2_sha1 = compute_hashes("image2.jpg")

print("MD5 Equal:", img1_md5 == img2_md5)
print("SHA1 Equal:", img1_sha1 == img2_sha1)