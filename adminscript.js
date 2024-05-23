document.addEventListener('DOMContentLoaded', () => {
    const newPostBtn = document.getElementById('newpbtn');
    const newForumBtn = document.getElementById('newfbtn');
    const modal = document.getElementById('modal');
    const closeModalBtn = document.querySelector('.closebtn');
    const modalTitle = document.getElementById('modaltitle');
    const inputTitle = document.getElementById('inputtitle');
    const inputContent = document.getElementById('inputcontent');
    const submitBtn = document.getElementById('submitbtn');
    const forumsContainer = document.getElementById('forums');
    let isCreatingPost = false;
    let replyingTo = null;

    loadPostsFromStorage();

    newPostBtn.addEventListener('click', () => {
        modalTitle.textContent = 'Create New Post';
        inputTitle.style.display = 'block';
        inputTitle.value = '';
        inputContent.value = '';
        modal.style.display = 'flex';
        isCreatingPost = true;
        replyingTo = null;
    });

    newForumBtn.addEventListener('click', () => {
        modalTitle.textContent = 'Create New Forum';
        inputTitle.style.display = 'block';
        inputTitle.value = '';
        inputContent.value = '';
        modal.style.display = 'flex';
        isCreatingPost = false;
        replyingTo = null;
    });

    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    submitBtn.addEventListener('click', () => {
        const title = inputTitle.value.trim();
        const content = inputContent.value.trim();

        if (isCreatingPost) {
            if (title && content) {
                const newElement = document.createElement('div');
                newElement.className = 'post';
                newElement.innerHTML = `
                    <div class="post-content">
                        <h3 class="posts">${title}</h3>
                        <p class="postcont">${content}</p>
                        <div class="button-container">
                            <button class="reply">Reply</button>
                            <button class="view-replies">View replies</button>
                            <button class="admindelete">Delete</button>
                        </div>
                        <div class="replies-container"></div>
                    </div>`;
                forumsContainer.appendChild(newElement);
                attachButtonListeners(newElement);
                modal.style.display = 'none';
                savePostsToStorage();
            } else {
                alert('Title and content cannot be empty.');
            }
        } else if (replyingTo !== null) {
            if (content) {
                const newElement = document.createElement('div');
                newElement.className = 'reply-content';
                newElement.innerHTML = `<p class="postcont">${content}</p>`;
                replyingTo.querySelector('.replies-container').appendChild(newElement);
                modal.style.display = 'none';
                replyingTo = null;
                savePostsToStorage();
            } else {
                alert('Content cannot be empty.');
            }
        }
    });

    function attachButtonListeners(postElement) {
        postElement.querySelector('.reply').addEventListener('click', () => {
            modalTitle.textContent = 'Reply to Post';
            inputTitle.style.display = 'none';
            inputTitle.value = '';
            inputContent.value = '';
            isCreatingPost = false;
            replyingTo = postElement;
            modal.style.display = 'flex';
        });

        postElement.querySelector('.view-replies').addEventListener('click', () => {
            const postContent = postElement.querySelector('.postcont').innerText;
            const replies = Array.from(postElement.querySelectorAll('.reply-content .postcont')).map(reply => reply.innerText);
            localStorage.setItem('viewingPost', JSON.stringify({
                title: postElement.querySelector('.posts').innerText,
                content: postContent,
                replies: replies
            }));
            window.location.href = 'viewing.html';
        });

        postElement.querySelector('.admindelete').addEventListener('click', () => {
            postElement.remove();
            savePostsToStorage();
        });
    }

    function savePostsToStorage() {
        const posts = Array.from(document.querySelectorAll('.post')).map(postElement => {
            const title = postElement.querySelector('.posts').innerText;
            const content = postElement.querySelector('.postcont').innerText;
            const replies = Array.from(postElement.querySelectorAll('.reply-content .postcont')).map(reply => reply.innerText);
            return { title, content, replies };
        });
        localStorage.setItem('posts', JSON.stringify(posts));
    }

    function loadPostsFromStorage() {
        const posts = JSON.parse(localStorage.getItem('posts')) || [];
        posts.forEach(post => {
            const newElement = document.createElement('div');
            newElement.className = 'post';
            newElement.innerHTML = `
                <div class="post-content">
                    <h3 class="posts">${post.title}</h3>
                    <p class="postcont">${post.content}</p>
                    <div class="button-container">
                        <button class="reply">Reply</button>
                        <button class="view-replies">View replies</button>
                        <button class="admindelete">Delete</button>
                    </div>
                    <div class="replies-container"></div>
                </div>`;
            post.replies.forEach(reply => {
                const replyElement = document.createElement('div');
                replyElement.className = 'reply-content';
                replyElement.innerHTML = `<p class="postcont">${reply}</p>`;
                newElement.querySelector('.replies-container').appendChild(replyElement);
            });
            forumsContainer.appendChild(newElement);
            attachButtonListeners(newElement);
        });
    }
});

