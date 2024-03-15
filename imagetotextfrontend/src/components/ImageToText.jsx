import React, { useState } from 'react';
import apiEndPoints from '../action/api';

const ImageToText = () => {
  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageUpload = (event) => {
    setSelectedImage(event.target.files[0]);
  };

  const upload = () => {
    const formData = new FormData();
    formData.append(
      "file",
      selectedImage,
      selectedImage.name
    );
    console.log("form date: ", formData);
    apiEndPoints.IMG_UPLOAD().imgUpload(formData)
      .then(async res => {
        console.warn(res);
        if (res.status === 200) {
          res = await res.data;
          console.log(res);
        }
      }).catch(error => {
        console.error(error.message);
      })
  }
  return (
    <div>
      <input type="file" onChange={handleImageUpload} />
      {selectedImage && <img className="selectedImg" src={selectedImage} alt="Selected" />}
      <div>
        <button onClick={upload}>Upload</button>
      </div>
    </div>
  );
};


export default ImageToText