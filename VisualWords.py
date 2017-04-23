#!/usr/bin/python

from PIL import Image
from PIL import ImageEnhance

def toAscii(pix):
    return chr(pix[0]), chr(pix[1]), chr(pix[2])

img = Image.open("test.png")
enhancer = ImageEnhance.Brightness(img)
darker = enhancer.enhance(1/2.0)
pix = list(darker.getdata())
print(map(toAscii, pix))