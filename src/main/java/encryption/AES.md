#AES(Advanced Encryption Standard) Encryption and Decryption
## Description:
This program provides the following cryptographic functionalities
 1. Encryption using AES
 2. Decryption using AES
 
 **High Level Algorithm :**
 1. Generate a AES key (specify the Key size during this phase) 
 2. Create the Cipher 
 3. To Encrypt : Initialize the Cipher for Encryption
 4. To Decrypt : Initialize the Cipher for Decryption
 
 ###Algorithm:
 **Step 1.** Generate an AES key using KeyGenerator Initialize the keysize to 128 bits (16 bytes).
 
 **Step 2.** Generate an Initialization Vector (IV) 
* a.  Use SecureRandom to generate random bits
 The size of the IV matches the blocksize of the cipher (128 bits for AES)
* b.  Construct the appropriate IvParameterSpec object for the data to pass to Cipher's init() method
>**Note:** Step-2 is only needed for CBC or CTR operation mode then follow Step-6a.

**Step 3.** Create a Cipher by specifying the following parameters
 * a. Algorithm name - AES(Advanced Encryption Standard)
 * b. Mode - it can be CBC/CTR/GCM/CCM, here it is CBC mode
 * c. Padding - e.g. PKCS7 or PKCS5
 
 **Step 4.** Initialize the Cipher for Encryption
 
 **Step 5.** Encrypt the Data 
 * a. Declare / Initialize the Data. Here the data is of type String 
 * b. Convert the Input Text to Bytes 
 * c. Encrypt the bytes using doFinal method
 
 **Step 6.** Decrypt the Data 
 * a. Initialize a new instance of Cipher for Decryption (normally don't reuse the same object). Be sure to obtain the same Initialization Vector(IV) bytes for CBC mode.
 * b. Decrypt the cipher bytes using doFinal method

