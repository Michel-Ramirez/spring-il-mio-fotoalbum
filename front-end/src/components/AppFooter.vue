<template >
    <footer>
        <div class="footer-main container">
            <div class="ps-3">
                <div>
                    <h4 class="name">Michel Ramirez Perez</h4>
                    <span class="text-light">Junior Full Stack web Developer</span>

                    <a href="mailto: michelramirez@outlook.it" class="d-block">Contattami</a>

                </div>

            </div>
            <div class="pe-3">
                <h5 class="text-light">Seguimi</h5>
                <div class="socials">
                    <a href="https://www.linkedin.com/in/michel-ramirez-perez" target="_blank" class="mb-3">
                        <i class="bi bi-linkedin me-3"></i>
                        <span>LinkedIn</span>
                    </a>
                    <a href="https://github.com/Michel-Ramirez" target="_blank">
                        <i class="bi bi-github me-3"></i>
                        <span>GitHub</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <p>Michel Ramirez Perez / © 2023 – 2024. Tutti i diritti sono riservati.</p>
        </div>
    </footer>
</template>
<script setup>

</script>
<style lang="scss" scoped>
footer {
    margin-top: 50px;
    background-color: #191919;

    .footer-main {
        padding: 30px 0;
        display: flex;
        justify-content: space-between;
    }

    .name {
        color: white;
        font-family: 'carattere';
    }

    .footer-bottom {
        padding: 20px 0;
        color: white;
        background-color: black;
        display: flex;
        justify-content: center;
    }
}

.socials {
    padding-top: 20px;
    border-top: 1px solid gray;
    display: flex;
    flex-direction: column;

    a {
        color: white;
        text-decoration: none;

        &:hover {
            color: blue;
        }
    }
}

@media screen and (max-width: 768px) {
    .footer-bottom p {
        padding: 0 20px;
    }
}
</style>