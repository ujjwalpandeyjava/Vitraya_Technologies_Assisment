import axios from "axios";

const ax = axios.create({
	baseURL: `http://localhost:8881`,
	timeout: 100000,
});
const apiEndPoints = {
	SIMPLE(url = '') {
		return {
			home: () => ax.get(url),
		}
	},
	AUTH(url = '/auth') {
		return {
			register: (payload) => ax.post(url + "/register", payload),
			login: (payload) => ax.post(url + '/login', payload)
		}
	},
	IMG_UPLOAD(url = "/img") {
		return {
			imgUpload: (data) => {
				return ax.post(url, data, {
					params: { ...data },
					headers: {
						// "Content-Type": "multipart/form-data"
					}
				})
			},

		}
	}
}

// Axios Interceptors
ax.interceptors.request.use(config => {
	// console.log("Req params" +config.params);
	return config;
},
	(error) => {
		// console.error("Error in request interceptor: ", error.message);
		return Promise.reject(error);
	});
ax.interceptors.response.use(response => {
	console.log("response data: " + response.data || response.json);
	return response;
}, function (error) {
	// console.error("Error in response interceptor: ", error);
	if (error?.response?.status === 400) {
		alert("===" + error?.response?.data?.Message)
	}
	return Promise.reject(error);
});
export default apiEndPoints;