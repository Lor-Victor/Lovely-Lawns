//document.addEventListener('DOMContentLoaded', () => {
//    const newPostBtn = document.getElementById('newpbtn');
//    const newForumBtn = document.getElementById('newfbtn');
//    const modal = document.getElementById('modal');
//    const closeModalBtn = document.querySelector('.closebtn');
//    const modalTitle = document.getElementById('modaltitle');
//    const inputTitle = document.getElementById('inputtitle');
//    const inputContent = document.getElementById('inputcontent');
//    const submitBtn = document.getElementById('submitbtn');
//    const forumsContainer = document.getElementById('forums');
//    let isCreatingPost = false;
//    let replyingTo = null;
//
//    loadPostsFromStorage();
//
//    newPostBtn.addEventListener('click', () => {
//        modalTitle.textContent = 'Create New Post';
//        inputTitle.style.display = 'block';
//        inputTitle.value = '';
//        inputContent.value = '';
//        modal.style.display = 'flex';
//        isCreatingPost = true;
//        replyingTo = null;
//    });
//
//    newForumBtn.addEventListener('click', () => {
//        modalTitle.textContent = 'Create New Forum';
//        inputTitle.style.display = 'block';
//        inputTitle.value = '';
//        inputContent.value = '';
//        modal.style.display = 'flex';
//        isCreatingPost = false;
//        replyingTo = null;
//    });
//
//    closeModalBtn.addEventListener('click', () => {
//        modal.style.display = 'none';
//    });
//
//    window.addEventListener('click', (event) => {
//        if (event.target === modal) {
//            modal.style.display = 'none';
//        }
//    });
//
//    submitBtn.addEventListener('click', () => {
//        const title = inputTitle.value.trim();
//        const content = inputContent.value.trim();
//
//        if (isCreatingPost) {
//            if (title && content) {
//                const newElement = document.createElement('div');
//                newElement.className = 'post';
//                newElement.innerHTML = `
//                    <div class="post-content">
//                        <h3 class="posts">${title}</h3>
//                        <p class="postcont">${content}</p>
//                        <div class="button-container">
//                            <button class="reply">Reply</button>
//                            <button class="view-replies">View replies</button>
//                            <button class="admindelete">Delete</button>
//                        </div>
//                        <div class="replies-container"></div>
//                    </div>`;
//                forumsContainer.appendChild(newElement);
//                attachButtonListeners(newElement);
//                modal.style.display = 'none';
//                savePostsToStorage();
//            } else {
//                alert('Title and content cannot be empty.');
//            }
//        } else if (replyingTo !== null) {
//            if (content) {
//                const newElement = document.createElement('div');
//                newElement.className = 'reply-content';
//                newElement.innerHTML = `<p class="postcont">${content}</p>`;
//                replyingTo.querySelector('.replies-container').appendChild(newElement);
//                modal.style.display = 'none';
//                replyingTo = null;
//                savePostsToStorage();
//            } else {
//                alert('Content cannot be empty.');
//            }
//        }
//    });
//
//    function attachButtonListeners(postElement) {
//        postElement.querySelector('.reply').addEventListener('click', () => {
//            modalTitle.textContent = 'Reply to Post';
//            inputTitle.style.display = 'none';
//            inputTitle.value = '';
//            inputContent.value = '';
//            isCreatingPost = false;
//            replyingTo = postElement;
//            modal.style.display = 'flex';
//        });
//
//        postElement.querySelector('.view-replies').addEventListener('click', () => {
//            const postContent = postElement.querySelector('.postcont').innerText;
//            const replies = Array.from(postElement.querySelectorAll('.reply-content .postcont')).map(reply => reply.innerText);
//            localStorage.setItem('viewingPost', JSON.stringify({
//                title: postElement.querySelector('.posts').innerText,
//                content: postContent,
//                replies: replies
//            }));
//            window.location.href = 'viewing.html';
//        });
//
//        postElement.querySelector('.admindelete').addEventListener('click', () => {
//            postElement.remove();
//            savePostsToStorage();
//        });
//    }
//
//    function savePostsToStorage() {
//        const posts = Array.from(document.querySelectorAll('.post')).map(postElement => {
//            const title = postElement.querySelector('.posts').innerText;
//            const content = postElement.querySelector('.postcont').innerText;
//            const replies = Array.from(postElement.querySelectorAll('.reply-content .postcont')).map(reply => reply.innerText);
//            return { title, content, replies };
//        });
//        localStorage.setItem('posts', JSON.stringify(posts));
//    }
//
//     submitBtn.addEventListener('click', () => {
//            const title = document.getElementById('inputtitle').value.trim();
//            const content = document.getElementById('inputcontent').value.trim();
//
//            // Send POST request to Spring Boot endpoint
//            fetch('http://localhost:8080/announcement/save', {
//                method: 'POST',
//                headers: {
//                    'Content-Type': 'application/json'
//                },
//                body: JSON.stringify({ title, content })
//            })
//            .then(response => {
//                if (response.ok) {
//                    // Announcement created successfully
//                    // You might handle this response in some way (e.g., display a success message)
//                    return response.json();
//                } else {
//                    // Handle errors (e.g., display an error message)
//                    throw new Error('Failed to create announcement.');
//                }
//            })
//            .catch(error => {
//                console.error('Error creating announcement:', error);
//                // Handle error (e.g., display an error message)
//            });
//        });
//
//});
//
