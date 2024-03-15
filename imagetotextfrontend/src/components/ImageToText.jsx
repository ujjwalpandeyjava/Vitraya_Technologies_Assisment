import React, { useState } from 'react';
import apiEndPoints from '../action/api';

const ImageToText = () => {
  const [selectedImage, setSelectedImage] = useState(null);
  const [text, setText] = useState("");

  const handleImageUpload = (event) => {
    setSelectedImage(event.target.files[0]);
    console.log(event.target.files[0]);
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
          console.log(typeof res, res);
          setText(res.text)
        }
      }).catch(error => {
        console.error(error.message);
      })
  }
  return (
    <div className='imageUpload'>
      <input type="file" onChange={handleImageUpload} />
      <div>
        {selectedImage && <>
          <img className="selectedImg" src={URL.createObjectURL(selectedImage)} alt="Selected" /> <br />
          Image name: -- {selectedImage.name}
        </>}
      </div>
      <div className='imgToText'>

      {text}
      </div>
      <div>
        <button onClick={upload}>Upload</button>
      </div>
    </div>
  );
};


export default ImageToText