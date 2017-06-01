#!/usr/bin/env python
import cv2
import numpy as np

img_rgb = cv2.imread('/root/algo/testsub/frame0.jpg') #retrive images from user/App //put the path of the folder??
img_gray = cv2.cvtColor(img_rgb, cv2.COLOR_BGR2GRAY)
template = cv2.imread('/root/algo/exa2.jpg',0) #retrive images from database (sample images)
w, h = template.shape[::-1]
res = cv2.matchTemplate(img_gray,template,cv2.TM_CCOEFF_NORMED)
threshold = 0.8
loc = np.where( res >= threshold)
for pt in zip(*loc[::-1]):
    cv2.rectangle(img_rgb, pt, (pt[0] + w, pt[1] + h), (0,0,255), 2)
cv2.imwrite('/root/algo/output/result.jpg',img_rgb) #should show it to the user in App
#cv2.imshow('detected' , img_bgr)
