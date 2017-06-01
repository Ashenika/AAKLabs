#!/usr/bin/env python
import cv2
import numpy as np

vidcap = cv2.VideoCapture('video.mp4')
success,image = vidcap.read()
count = 0
success = True
while success:
  success,image = vidcap.read()
  print 'Read a new frame: ', success
  cv2.imwrite("frame%d.jpg" % count, image)     # save frame as JPEG file
  count += 1

img_rgb = cv2.imread('frame150.jpg')
img_gray = cv2.cvtColor(img_rgb, cv2.COLOR_BGR2GRAY)
template = cv2.imread('exa2.jpg',0) #template
w, h = template.shape[::-1]
res = cv2.matchTemplate(img_gray,template,cv2.TM_CCOEFF_NORMED)
threshold = 0.8
loc = np.where( res >= threshold)
for pt in zip(*loc[::-1]):
    cv2.rectangle(img_rgb, pt, (pt[0] + w, pt[1] + h), (0,0,255), 2)
cv2.imwrite('result.jpg',img_rgb) #should show it to the user in App
#cv2.imshow('detected' , img_bgr)

print("Done!")
