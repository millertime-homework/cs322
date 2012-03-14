	.align 4
test_foo:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (BINOP + (PARAM 1) (PARAM 2))]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=3, max_args=3
	save %sp,-104,%sp
! [MOVE (VAR 1) (CONST 1)]
	mov 1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 1) (CALL (NAME test_foo) ( (PARAM 0) (CONST 1) (CONST 2)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [1],%l1
	st %l1,[%sp+68]
	ld [2],%l2
	st %l2,[%sp+68]
	call test_foo
	nop
	mov %o0,%l3
!>> Temp t1 assigned to reg %l4
	mov %o0,%l4
! [MOVE (VAR 2) (TEMP 1)]
	mov %l4,%l3
	st %l3,[%fp-8]
! [MOVE (VAR 3) (BINOP * (CONST 2) (CONST 3))]
	mov 2,%l3
	mov 3,%l5
	smul %l3,%l5,%l3
	mov %l3,%l3
	st %l3,[%fp-12]
! [CALLST (NAME print) ( (VAR 1))]
	ld [%fp-4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 2))]
	ld [%fp-8],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 3))]
	ld [%fp-12],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  6
!Total insts: 48
