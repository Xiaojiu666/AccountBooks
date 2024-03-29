# PyElliptic

PyElliptic is a high level wrapper for the cryptographic library : OpenSSL.
Under the Berkeley software distribution license (see LICENSE).

Python3 compatible. For GNU/Linux and Windows.
Require OpenSSL

## Features

### Asymmetric cryptography using Elliptic Curve Cryptography (ECC)

* Key agreement : ECDH
* Digital signatures : ECDSA
* Hybrid encryption : ECIES (like RSA)

### Symmetric cryptography

* AES-128 (CBC, OFB, CFB, CTR)
* AES-256 (CBC, OFB, CFB, CTR)
* Blowfish (CFB and CBC)
* RC4

### Other

* CSPRNG
* HMAC (using SHA512)
* PBKDF2 (SHA256 and SHA512)

## Example

```python
#!/usr/bin/python

from binascii import hexlify

import pyelliptic

# Symmetric encryption
iv = pyelliptic.Cipher.gen_IV('aes-256-cfb')
ctx = pyelliptic.Cipher("secretkey", iv, 1, ciphername='aes-256-cfb')

ciphertext = ctx.update('test1')
ciphertext += ctx.update('test2')
ciphertext += ctx.final()

ctx2 = pyelliptic.Cipher("secretkey", iv, 0, ciphername='aes-256-cfb')
print(ctx2.ciphering(ciphertext))

# Asymmetric encryption
alice = pyelliptic.ECC() # default curve: sect283r1
bob = pyelliptic.ECC(curve='sect571r1')

ciphertext = alice.encrypt("Hello Bob", bob.get_pubkey(),
                           ephemcurve='sect571r1')
print(bob.decrypt(ciphertext))

signature = bob.sign("Hello Alice")
# alice's job :
print(pyelliptic.ECC(pubkey=bob.get_pubkey(),
                     curve='sect571r1').verify(signature, "Hello Alice"))

# ERROR !!!
try:
    key = alice.get_ecdh_key(bob.get_pubkey())
except:
    print("For ECDH key agreement, the keys must be defined on the same curve !")

alice = pyelliptic.ECC(curve='sect571r1')
print(hexlify(alice.get_ecdh_key(bob.get_pubkey())))
print(hexlify(bob.get_ecdh_key(alice.get_pubkey())))
```
