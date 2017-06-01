import cv2
cap = cv2.VideoCapture('C:\Users\Kaura\Documents\IIT\2nd Year\2nd SEM\SDGP\Proto type Submission/SMART TOOTH DETECTOR.mp4')
count = 0
while cap.isOpened():
    ret,frame = cap.read()
    cv2.imshow('window-name',frame)
    cv2.imwrite("frame%d.jpg" % count, frame)
    #Find way to save this imsge in cloud DB 
    count = count + 1
    if cv2.waitKey(10) & 0xFF == ord('q'):
        break


cap.release()