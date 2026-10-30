@echo off
chcp 65001 >nul
cd /d "E:\MCR\GTY"

echo ===================================================
echo 终极推送脚本 (修复版)
echo 目标仓库: bii20081Aztec/CSSAHUV
echo ===================================================
echo.

:: 配置用户信息 (注意：请把邮箱换成你真实的GitHub邮箱，否则不算你的贡献)
echo [1/5] 配置 Git 用户...
git config --global user.name "bii20081Aztec"
git config --global user.email "bii20081@outlook.com"
echo 完成
echo.

:: 初始化仓库
echo [2/5] 初始化本地仓库...
if exist ".git" (
    echo 已存在 .git，跳过初始化
) else (
    git init
    echo 初始化完成
)
echo.

:: 设置远程仓库
echo [3/5] 设置远程仓库地址...
git remote remove origin 2>nul
git remote add origin https://github.com/bii20081Aztec/CSSAHUV
echo 远程地址已设置
echo.

:: 提交更改 (加了 git add . 这样你改的代码才能提交上去，而不只是空提交)
echo [4/5] 提交更改...
git add .
git commit -m "自动备份提交 - %date% %time%"
echo 提交完成
echo.

:: 强制推送 (修复了分支问题，自动获取当前分支名，不再写死 main 或 master)
echo [5/5] 强制推送到远程仓库...
:: 获取当前分支名
for /f "tokens=*" %%i in ('git branch --show-current') do set CURRENT_BRANCH=%%i
git push -u origin %CURRENT_BRANCH% --force

:: 判断是否推送成功
if %errorlevel% equ 0 (
    echo.
    echo ===================================================
    echo 推送成功！代码已覆盖到 GitHub。
    echo ===================================================
) else (
    echo.
    echo ===================================================
    echo [错误] 推送失败！请检查网络或权限。
    echo ===================================================
)

echo.
pause